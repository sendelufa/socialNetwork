package ru.skillbox.socialnetwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skillbox.socialnetwork.api.response.AbstractResponse;
import ru.skillbox.socialnetwork.api.response.ErrorApi;
import ru.skillbox.socialnetwork.api.response.FileUploadResponseApi;
import ru.skillbox.socialnetwork.api.response.ResponseApi;
import ru.skillbox.socialnetwork.dao.PersonDAO;
import ru.skillbox.socialnetwork.model.Person;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StorageService {

    @Autowired
    private PersonDAO personDAO;

    @Value("${upload.path}")
    private String uploadPath;

    private long timeResult;
    private String uuidFile = UUID.randomUUID().toString();
    private String resultFileName;

    public AbstractResponse uploadFileResponse(String type, MultipartFile file, FileUploadResponseApi fura) throws IOException {
        AbstractResponse response;

        if (type.equals("IMAGE") && file != null) {
            uploadFile(type, file);
            response = new ResponseApi("string", System.currentTimeMillis(), getFileUploadResponseApi(file));
            response.setSuccess(true);
            return response;
        }
        response = new ErrorApi("invalid request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    private void uploadFile(String type, MultipartFile file) throws IOException {

        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
        {
            uploadDir.mkdir();
        }

        resultFileName = uuidFile + "." + file.getOriginalFilename();
        long period = System.currentTimeMillis();

        if(type.equals("IMAGE")) {
            file.transferTo(new File(uploadPath + "/" + resultFileName));
        }
        timeResult = System.currentTimeMillis() - period;
    }

    public FileUploadResponseApi getFileUploadResponseApi(MultipartFile file) throws IOException {

        FileUploadResponseApi fura = new FileUploadResponseApi();
        fura.setId(uuidFile);
        fura.setOwnerId(getCurrentPersonFromSecurityContext().getId());
        fura.setFileName(file.getName());
        fura.setRawFileURL(file.getOriginalFilename());
        fura.setRelativeFilePath("/" + resultFileName);
        fura.setCreatedAt((int) timeResult);
        fura.setFileType(FileUploadResponseApi.fileTypes.IMAGE);
        fura.setFileFormat(file.getContentType());
        fura.setBytes(file.getBytes().length);

        return fura;
    }

    private Person getCurrentPersonFromSecurityContext(){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return personDAO.getPersonByEmail(user.getUsername());

    }
}
