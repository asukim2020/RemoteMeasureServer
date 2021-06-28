package kr.co.greentech.measure.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.greentech.measure.domain.MeasureFile;
import kr.co.greentech.measure.service.FilePath;
import kr.co.greentech.measure.service.FileUploadDownloadService;
import kr.co.greentech.measure.util.FileUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadDownloadService service;

    @GetMapping("/")
    public String controllerMain() {
        return "Hello~ File Upload Test.";
    }

    private FileUploadResponse uploadFile(MultipartFile file, FilePath path) {
        String fileName = service.storeFile(file, path);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/file/download" + path + "/")
                .path(fileName)
                .toUriString();
        MeasureFile measureFile = new MeasureFile();
        measureFile.setTime((new Date()).getTime());
        measureFile.setType(path.toString().replace("/", ""));
        measureFile.setUrl(fileDownloadUri);
        service.saveMeasureFile(measureFile);

        return new FileUploadResponse(
                fileName,
                fileDownloadUri,
                file.getContentType(),
                file.getSize()
        );
    }

    @PostMapping("/file/delete")
    private void deleteFiles(
            @RequestParam("time") Long time
    ) {
        service.deleteFiles(new Date(time));
    }

    @GetMapping("/file/find")
    public List<MeasureFile> findTypeAll(
            @RequestParam("type") String type,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime
    ) {
        return service.findTypeAll(type, startTime, endTime);
    }

    @PostMapping("/file/upload/slope")
    public FileUploadResponse slopeUploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return uploadFile(file, FilePath.SLOPE);
    }

    @PostMapping("/file/upload/accel")
    public FileUploadResponse accelUploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return uploadFile(file, FilePath.ACCEL);
    }

    @PostMapping("/file/upload/trigger")
    public FileUploadResponse triggerUploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return uploadFile(file, FilePath.TRIGGER);
    }

    @PostMapping("/file/upload/request")
    public FileUploadResponse requestUploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return uploadFile(file, FilePath.REQUEST);
    }


//    @PostMapping("/uploadMultipleFiles")
//    public List<FileUploadResponse> uploadMultipleFiles(
//            @RequestParam("files") MultipartFile[] files
//    ){
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    private ResponseEntity<Resource> downloadFile(
            String fileName,
            HttpServletRequest request,
            FilePath path,
            Date date
    ) {
        // Load file as Resource
        Resource resource = service.loadFileAsResource(fileName, path, date);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request
                    .getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                ).body(resource);
    }

    @GetMapping("/file/download/slope/{fileName:.+}")
    public ResponseEntity<Resource> slopeDownloadFile(
            @PathVariable String fileName,
            HttpServletRequest request,
            @RequestParam("time") Long time

    ){
        return downloadFile(fileName, request, FilePath.SLOPE, new Date(time));
    }

    @GetMapping("/file/download/accel/{fileName:.+}")
    public ResponseEntity<Resource> accelDownloadFile(
            @PathVariable String fileName,
            HttpServletRequest request,
            @RequestParam("time") Long time
    ){
        return downloadFile(fileName, request, FilePath.ACCEL, new Date(time));
    }

    @GetMapping("/file/download/trigger/{fileName:.+}")
    public ResponseEntity<Resource> triggerDownloadFile(
            @PathVariable String fileName,
            HttpServletRequest request,
            @RequestParam("time") Long time
    ){
        return downloadFile(fileName, request, FilePath.TRIGGER, new Date(time));
    }

    @GetMapping("/file/download/request/{fileName:.+}")
    public ResponseEntity<Resource> requestDownloadFile(
            @PathVariable String fileName,
            HttpServletRequest request,
            @RequestParam("time") Long time
    ){
        return downloadFile(fileName, request, FilePath.REQUEST, new Date(time));
    }
}

