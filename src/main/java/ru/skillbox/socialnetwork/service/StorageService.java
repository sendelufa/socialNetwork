package ru.skillbox.socialnetwork.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {

//    @Autowired
//    private PersonDAO personDAO;
    @Autowired
    private AccountService accountService;

//    @Value("${upload.path}")
//    private String uploadPath;

    private long timeResult;
//    private String uuidFile = UUID.randomUUID().toString();
//    private String resultFileName;

    private Map uploadResult;

    public AbstractResponse uploadFileResponse(String type, MultipartFile file) throws IOException {
        AbstractResponse response;

        if(file == null){
            response = new ResponseApi("string", System.currentTimeMillis(), new ResponseApi.Message("nothing to upload"));
            response.setSuccess(true);
            return response;
        }
       // if (type.equals("IMAGE") && file != null) {
        if (type.equals("IMAGE")) {
            //uploadFile(type, file);
            byte[] bytes = file.getBytes();
            response = new ResponseApi("string", System.currentTimeMillis(), getFileUploadResponseApi(file, uploadFile(type, file), bytes));
            response.setSuccess(true);
            return response;
        }
        response = new ErrorApi("invalid request", "Wrong type of entity");
        response.setSuccess(false);
        return response;
    }

    private Map uploadFile(String type, MultipartFile file) throws IOException {

//        File uploadDir = new File(uploadPath);
//        if(!uploadDir.exists())
//        {
//            uploadDir.mkdir();
//        }
//
//        resultFileName = uuidFile + "." + file.getOriginalFilename();
//        long period = System.currentTimeMillis();
//
//        if(type.equals("IMAGE")) {
//            file.transferTo(new File(uploadPath + "/" + resultFileName));
//        }
//        timeResult = System.currentTimeMillis() - period;

        Cloudinary cloudinary = new Cloudinary("cloudinary://556571463216775:eHhOL1Q3s-qNvMyfuYDXzvtNLyM@ryker");
        long period = System.currentTimeMillis();
        if(type.equals("IMAGE")){
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        }
        timeResult = System.currentTimeMillis() - period;

        return uploadResult;
    }

    public FileUploadResponseApi getFileUploadResponseApi(MultipartFile file, Map uploadResult, byte[] bytes) throws IOException {

        FileUploadResponseApi fura = new FileUploadResponseApi();
        //fura.setId(uuidFile);
        fura.setId((String)uploadResult.get("url"));
        fura.setOwnerId(accountService.getCurrentUser().getId());
        fura.setFileName(file.getName());
        fura.setRawFileURL(file.getOriginalFilename());
        fura.setRelativeFilePath((String)uploadResult.get("url"));
        fura.setCreatedAt((int) timeResult);
        fura.setFileType(FileUploadResponseApi.fileTypes.IMAGE);
        fura.setFileFormat(file.getContentType());
        fura.setBytes(bytes);

        return fura;
    }


}
