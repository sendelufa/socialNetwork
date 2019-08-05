package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.request.DialogUsersApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.DialogApi;
import ru.skillbox.socialnetwork.api.response.DialogInviteLink;
import ru.skillbox.socialnetwork.api.response.DialogListApi;
import ru.skillbox.socialnetwork.api.response.DialogMessageListApi;
import ru.skillbox.socialnetwork.api.response.DialogUserShortListApi;
import ru.skillbox.socialnetwork.api.response.MessageListItemApi;
import ru.skillbox.socialnetwork.api.response.MessageSendRequestBodyApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.DialogDao;
import ru.skillbox.socialnetwork.dao.MessageDao;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.mapper.DialogMapper;
import ru.skillbox.socialnetwork.model.Dialog;
import ru.skillbox.socialnetwork.model.Message;
import ru.skillbox.socialnetwork.model.Person;
import ru.skillbox.socialnetwork.model.enumeration.ReadStatusMessage;
import ru.skillbox.socialnetwork.utils.PredicateOpt;

@Service
public class DialogService implements PredicateOpt {

   private final String ERROR_DIALOG_NOT_EXIST = "This dialog doesn't exist";
   private final String ERROR_INVITE_NOT_EQUALS = "invite not matches";
   @Autowired
   private MessageDao messageDao;
   @Autowired
   private DialogDao dialogDao;
   @Autowired
   private AccountService accountService;
   @Autowired
   private DialogMapper dialogMapper;
   @Autowired
   private PersonDAO personDAO;

   public AbstractResponse deleteDialogMessages(int dialogId, int messageId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      AbstractResponse response;
      if (dialog != null) {
         Message message = messageDao.getMessageById(messageId);
         if (message != null && dialog.getMessages().contains(message)) {
            message.setDeleted(true);
            messageDao.updateMessage(message);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = new ResponseApi("This message doesn't exist", System.currentTimeMillis(),
                new ResponseApi.Message("invalid_request"));
            response.setSuccess(false);
         }
      } else {
         response = new ResponseApi(ERROR_DIALOG_NOT_EXIST, System.currentTimeMillis(),
             new ResponseApi.Message("invalid_request"));
         response.setSuccess(false);
      }
      return response;
   }

   public AbstractResponse editDialogMessage(int dialogId, int messageId,
       MessageSendRequestBodyApi messageSend) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      AbstractResponse response;
      if (dialog != null) {
         Message message = messageDao.getMessageById(messageId);
         if (message != null && dialog.getMessages().contains(message)) {
            message.setMessageText(messageSend.getMessage_text());
            messageDao.updateMessage(message);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = new ResponseApi("This message doesn't exist", System.currentTimeMillis(),
                new ResponseApi.Message("invalid_request"));
            response.setSuccess(false);
         }
      } else {
         response = new ResponseApi(ERROR_DIALOG_NOT_EXIST, System.currentTimeMillis(),
             new ResponseApi.Message("invalid_request"));
         response.setSuccess(false);
      }
      return response;
   }

   public AbstractResponse recoverDialogMessage(int dialogId, int messageId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      AbstractResponse response;
      if (dialog != null) {
         Message message = messageDao.getMessageById(messageId);
         if (message != null && dialog.getMessages().contains(message)) {
            message.setDeleted(false);
            messageDao.updateMessage(message);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = new ResponseApi("This message doesn't exist", System.currentTimeMillis(),
                new ResponseApi.Message("invalid_request"));
            response.setSuccess(false);
         }
      } else {
         response = new ResponseApi(ERROR_DIALOG_NOT_EXIST, System.currentTimeMillis(),
             new ResponseApi.Message("invalid_request"));
         response.setSuccess(false);
      }
      return response;
   }

   public AbstractResponse readDialogMessage(int dialogId, int messageId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      AbstractResponse response;
      if (dialog != null) {
         Message message = messageDao.getMessageById(messageId);
         if (message != null && dialog.getMessages().contains(message)) {
            message.setReadStatus(ReadStatusMessage.READ);
            messageDao.updateMessage(message);
            dialog.setUnreadCount(dialog.getUnreadCount() - 1);
            dialogDao.updateDialog(dialog);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = new ResponseApi("This message doesn't exist", System.currentTimeMillis(),
                new ResponseApi.Message("invalid_request"));
            response.setSuccess(false);
         }
      } else {
         response = new ResponseApi(ERROR_DIALOG_NOT_EXIST, System.currentTimeMillis(),
             new ResponseApi.Message("invalid_request"));
         response.setSuccess(false);
      }
      return response;
   }

   public AbstractResponse getDialogs(String query, int offset, int itemPerPage) {
      AbstractResponse response;
      List<Dialog> dialogs = dialogDao.getDialogsWithParameters(query, offset, itemPerPage);
      if (dialogs != null) {
         List<DialogApi> dialogApis = dialogs.stream().map(e -> dialogMapper.toApi(e))
             .collect(Collectors.toList());
         DialogListApi dialogListApi = new DialogListApi();
         dialogListApi.setDialogs(dialogApis);
         response = dialogListApi;
         response.setSuccess(true);
      } else {
         response = new ResponseApi("This dialogs doesn't exist", System.currentTimeMillis(),
             new ResponseApi.Message("invalid_request"));
         response.setSuccess(false);
      }
      return response;
   }

   public AbstractResponse putDialogs(DialogUserShortListApi dialogUsers) {
      Dialog dialog = new Dialog();
      List<Person> personList = new ArrayList<>();
      for (Integer i : dialogUsers.getUserIds()) {
         personList.add(personDAO.getPersonById(i));
      }
      dialog.setPersonList(personList);
      dialogDao.addDialog(dialog);
      return getOKResponseApi(dialogMapper.toApi(dialog));
   }

   public ResponseApi putPersons(int dialogId, DialogUsersApi dialogUsersApi) {
      List<Integer> personIds = new ArrayList<>();
      Dialog dialog = dialogDao.getDialogById(dialogId);

      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }

      Arrays.stream(dialogUsersApi.getUserIds())
          .mapToObj(personDAO::getPersonById)
          .filter(Objects::nonNull)
          .filter(PredicateOpt.not(Person::isBlocked))
          .filter(PredicateOpt.not(p -> dialog.getPersonList().contains(p)))
          .forEach(p -> {
             dialogDao.addPersonToDialog(dialog, p);
             personIds.add(p.getId());
          });

      return getOKResponseApi(
          new DialogUserShortListApi(personIds.stream().mapToInt(i -> i).toArray()));
   }

   public ResponseApi removePersons(int dialogId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      int[] personIds = dialog.getPersonList().stream()
          .map(Person::getId)
          .mapToInt(i -> i)
          .toArray();

      dialog.getPersonList().clear();
      dialogDao.updateDialog(dialog);

      return getOKResponseApi(
          new DialogUserShortListApi(personIds));
   }

   public ResponseApi getInviteLink(int dialogId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      return getOKResponseApi(
          new DialogInviteLink(dialog.getInviteCode()));
   }

   public ResponseApi join(int dialogId, String inviteLink) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      if (!dialog.getInviteCode().equals(inviteLink)) {
         return getErrorResponse(ERROR_INVITE_NOT_EQUALS);
      }

      int[] personIds = dialog.getPersonList().stream()
          .map(Person::getId)
          .mapToInt(i -> i)
          .toArray();

      dialogDao.addPersonToDialog(dialog, accountService.getCurrentUser());

      return getOKResponseApi(
          new DialogUserShortListApi(personIds));
   }

   public ResponseApi getMessages(int dialogId, String query, int offset, int itemPerPage) {
      List<Message> messageList = dialogDao.getMessages(dialogId, query, offset, itemPerPage);
      DialogMessageListApi messageListApi = new DialogMessageListApi();
      messageListApi.setOffset(offset);
      messageListApi.setPerPage(itemPerPage);
      messageListApi.setTotal(messageList.size());
      messageListApi.setError("ok");
      messageListApi.setTimestamp(System.currentTimeMillis());

      List<MessageListItemApi> messageListApiListItem = new ArrayList<>();

      for (Message m : messageList) {
         MessageListItemApi messageListItemApi = new MessageListItemApi(
             m.getId(), m.getTime().getTime(), m.getMessageText(), m.getReadStatus(),
             accountService.getCurrentUser().equals(m.getAuthor()));
         messageListApiListItem.add(messageListItemApi);
      }

      messageListApi.setData(messageListApiListItem);

      return messageListApi;
   }

   private ResponseApi getOKResponseApi(AbstractResponse abstractResponse) {
      return new ResponseApi("ok", System.currentTimeMillis(), abstractResponse);
   }

   private ResponseApi getErrorResponse(String error) {
      return new ResponseApi(error, System.currentTimeMillis(),
          new ResponseApi.Message("invalid_request"));
   }
}
