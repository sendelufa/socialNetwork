package ru.skillbox.socialnetwork.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.socialnetwork.api.request.DialogUsersApi;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ActivityApi;
import ru.skillbox.socialnetwork.api.response.DialogActivityChangeApi;
import ru.skillbox.socialnetwork.api.response.DialogApi;
import ru.skillbox.socialnetwork.api.response.DialogIdApi;
import ru.skillbox.socialnetwork.api.response.DialogInviteLink;
import ru.skillbox.socialnetwork.api.response.DialogLastActivityApi;
import ru.skillbox.socialnetwork.api.response.DialogListApi;
import ru.skillbox.socialnetwork.api.response.DialogMessageListApi;
import ru.skillbox.socialnetwork.api.response.DialogUserShortListApi;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.MessageApi;
import ru.skillbox.socialnetwork.api.response.MessageApi.readStatuses;
import ru.skillbox.socialnetwork.api.response.MessageListItemApi;
import ru.skillbox.socialnetwork.api.response.MessageRecipientApi;
import ru.skillbox.socialnetwork.api.response.MessageSendRequestBodyApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.api.response.UnreadedCountApi;
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
   private final String ERROR_PERSON_NOT_EXIST = "This person doesn't exist";
   private final String ERROR_MESSAGE_NOT_EXIST = "This message doesn't exist";
   private final String ERROR_PERSON_NOT_IN_DIALOG = "Dialog not contains person";
   private final String ERROR_INVITE_NOT_EQUALS = "invite not matches";
   private final String ERROR_USER_NOT_OWNER_DIALOG = "Person %s not an owner of dialog %s";
   private final String ERROR_USER_NOT_OWNER_MESSAGE = "Person %s not an owner of message %s";
   private final String INVITE_SYMBOLS = "ABCDEFGHIJKLOMPQRSTUVWXYZ1234567890";
   private final int INVITE_LENGTH = 18;
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

   private static String generateInviteLink(Random rng, String characters, int length) {
      char[] text = new char[length];
      for (int i = 0; i < length; i++) {
         text[i] = characters.charAt(rng.nextInt(characters.length()));
      }
      return new String(text);
   }

   public AbstractResponse deleteDialogMessages(int dialogId, int messageId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      AbstractResponse response;
      if (dialog != null) {
         Message message = messageDao.getMessageById(messageId);
         if (message != null && dialog.getMessages().contains(message)) {
            if (!message.getAuthor().equals(accountService.getCurrentUser())) {
               return getErrorResponse(String.format(ERROR_USER_NOT_OWNER_MESSAGE,
                   accountService.getCurrentUser().getId(), message.getId()));
            }
            message.setDeleted(true);
            messageDao.updateMessage(message);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = getErrorResponse(ERROR_MESSAGE_NOT_EXIST);
         }
      } else {
         response = getErrorResponse(ERROR_DIALOG_NOT_EXIST);
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
            if (!message.getAuthor().equals(accountService.getCurrentUser())) {
               return getErrorResponse(String.format(ERROR_USER_NOT_OWNER_MESSAGE,
                   accountService.getCurrentUser().getId(), message.getId()));
            }
            message.setMessageText(messageSend.getMessage_text());
            messageDao.updateMessage(message);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = getErrorResponse(ERROR_MESSAGE_NOT_EXIST);
         }
      } else {
         response = getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      return response;
   }

   public AbstractResponse recoverDialogMessage(int dialogId, int messageId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      AbstractResponse response;
      if (dialog != null) {
         Message message = messageDao.getMessageById(messageId);
         if (message != null && dialog.getMessages().contains(message)) {
            if (!message.getAuthor().equals(accountService.getCurrentUser())) {
               return getErrorResponse(String.format(ERROR_USER_NOT_OWNER_MESSAGE,
                   accountService.getCurrentUser().getId(), message.getId()));
            }
            message.setDeleted(false);
            messageDao.updateMessage(message);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = getErrorResponse(ERROR_MESSAGE_NOT_EXIST);
         }
      } else {
         response = getErrorResponse(ERROR_DIALOG_NOT_EXIST);
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
            dialog.setUnreadCount(dialog.getUnreadCount() > 0 ? dialog.getUnreadCount() - 1 : 0);
            dialogDao.updateDialog(dialog);
            response = new ResponseApi("string", System.currentTimeMillis(),
                new ResponseApi.Message("ok"));
            response.setSuccess(true);
         } else {
            response = getErrorResponse(ERROR_MESSAGE_NOT_EXIST);
         }
      } else {
         response = getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      return response;
   }

   public AbstractResponse getDialogs(String query, int offset, int itemPerPage) {
      AbstractResponse response;
      List<Dialog> dialogs = dialogDao.getDialogsWithParameters(query, offset, itemPerPage);
      if (dialogs != null) {
         List<DialogApi> dialogApis = dialogs.stream().map(d ->
             {
                List<Message> messages = dialogDao.getMessages(d.getId(), "", 0, 1);
                MessageApi messageApi = null;
                if (messages.size() > 0) {
                   Message m = messages.get(0);
                   messageApi = new MessageApi(m.getId(), m.getTime().getTime(), m.getAuthor().getId(),
                       m.getRecipient().getId(), m.getMessageText(),
                       m.getReadStatus() == ReadStatusMessage.SENT ? readStatuses.SENT
                           : readStatuses.READ,
                       accountService.getCurrentUser().equals(m.getAuthor()),
                       new MessageRecipientApi(m.getRecipient().getId(),
                           m.getRecipient().getFirstName(), m.getRecipient().getLastName(),
                           m.getRecipient().getPhoto(), m.getRecipient().getLastOnlineTime().getTime())
                   );
                }
                return new DialogApi(d.getId(),
                    d.getUnreadCount(), messageApi);
             }
         ).collect(Collectors.toList());
         DialogListApi dialogListApi = new DialogListApi();
         dialogListApi.setData(dialogApis);
         dialogListApi.setTotal(dialogApis.size());
         dialogListApi.setOffset(offset);
         dialogListApi.setPerPage(itemPerPage);
         response = dialogListApi;
      } else {
         response = getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      response.setSuccess(true);
      return response;
   }

   public AbstractResponse putDialogs(DialogUsersApi dialogUsers) {
      Dialog dialog = new Dialog();
      List<Person> personList = new ArrayList<>();
      for (Integer i : dialogUsers.getUserIds()) {
         personList.add(personDAO.getPersonById(i));
      }
      personList.add(accountService.getCurrentUser());
      dialog.setPersonList(personList);
      dialog.setInviteCode(generateInviteLink(new Random(), INVITE_SYMBOLS, INVITE_LENGTH));
      dialog.setOwnerId(accountService.getCurrentUser().getId());
      dialogDao.addDialog(dialog);
      return getOKResponseApi(new DialogIdApi(dialog.getId()));
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

   public ResponseApi sendMessage(int dialogId, String text) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      if (!dialog.getPersonList().contains(accountService.getCurrentUser())) {
         return getErrorResponse(ERROR_PERSON_NOT_IN_DIALOG);
      }
      Message message = new Message();
      message.setTime(new Date());
      message.setAuthor(accountService.getCurrentUser());
      //TODO what recipient in dialog? cap is person with id=1
      message.setRecipient(personDAO.getPersonById(1));
      message.setMessageText(text);
      message.setReadStatus(ReadStatusMessage.SENT);
      message.setDialogId(dialogId);
      messageDao.addMessage(message);

      MessageListItemApi messageListItemApi = new MessageListItemApi(
          message.getId(), message.getTime().getTime(), message.getMessageText(),
          message.getReadStatus(),
          accountService.getCurrentUser().equals(message.getAuthor()));

      return new ResponseApi("ok", System.currentTimeMillis(), messageListItemApi);
   }

   public ResponseApi getLastActivity(int dialogId, int personId) {
      DialogLastActivityApi lastActivityApi = new DialogLastActivityApi();
      Dialog dialog = dialogDao.getDialogById(dialogId);
      Person person = personDAO.getPersonById(personId);
      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }
      if (person == null) {
         return getErrorResponse(ERROR_PERSON_NOT_EXIST);
      }
      if (!dialog.getPersonList().contains(person)) {
         return getErrorResponse(ERROR_PERSON_NOT_IN_DIALOG);
      }
      //TODO what value LastActivity must contain? current - last Message timestamp
      Optional<Message> lastMessageOptional =
          dialog.getMessages()
              .stream()
              .filter(m -> m.getAuthor().equals(person))
              .max(Comparator.comparing(Message::getTime));

      long lastActivity = lastMessageOptional.map(message -> message.getTime().getTime())
          .orElse(0L);

      lastActivityApi.setLastActivity(lastActivity);
      lastActivityApi.setOnline(person.isOnline());

      return new ResponseApi("ok", System.currentTimeMillis(), lastActivityApi);
   }

   public ResponseApi setPrintStatus(int dialogId, int personId) {
      //TODO not fully realised
      DialogActivityChangeApi activityChangeApi = new DialogActivityChangeApi();
      activityChangeApi.setMessage("message");
      return new ResponseApi("ok", System.currentTimeMillis(), activityChangeApi);
   }

   public ResponseApi deleteDialog(int dialogId) {
      Dialog dialog = dialogDao.getDialogById(dialogId);
      if (dialog == null) {
         return getErrorResponse(ERROR_DIALOG_NOT_EXIST);
      }

      if (accountService.getCurrentUser().getId() != dialog.getOwnerId()) {
         return getErrorResponse(String.format(ERROR_USER_NOT_OWNER_DIALOG,
             accountService.getCurrentUser().getId(), dialogId));
      }
      dialog.setDeleted(true);
      dialogDao.updateDialog(dialog);

      DialogIdApi dialogIdApi = new DialogIdApi(dialogId);

      return new ResponseApi("ok", System.currentTimeMillis(), dialogIdApi);
   }

   private ResponseApi getOKResponseApi(AbstractResponse abstractResponse) {
      return new ResponseApi("ok", System.currentTimeMillis(), abstractResponse);
   }

   private ResponseApi getErrorResponse(String error) {
      return new ResponseApi(error, System.currentTimeMillis(),
          new ResponseApi.Message("invalid_request"));
   }

   public AbstractResponse getUnreadedMessages() {
      Person person = accountService.getCurrentUser();
      AbstractResponse response;
      if (person != null) {
         int countUnreadMessages =
             person.getDialogList().stream()
                 .filter(PredicateOpt.not(Dialog::isDeleted))
                 .mapToInt(Dialog::getUnreadCount)
                 .sum();
         response = new ResponseApi("string", System.currentTimeMillis(),
             new UnreadedCountApi(countUnreadMessages));
         response.setSuccess(true);
      } else {
         response = getErrorResponse(ERROR_PERSON_NOT_EXIST);
      }
      return response;
   }

   public AbstractResponse getActivity(int id, int userId) {
      AbstractResponse response;
      Person person = personDAO.getPersonById(userId);
      if (person != null) {
         response = new ResponseApi("ok", System.currentTimeMillis(),
             new ActivityApi(person.isOnline(), person.getLastOnlineTime().getTime()));
         response.setSuccess(true);
      } else {
         response = new ErrorApi("invalid_request", "This person doesn't exist");
         response.setSuccess(false);
      }
      return response;
   }
}
