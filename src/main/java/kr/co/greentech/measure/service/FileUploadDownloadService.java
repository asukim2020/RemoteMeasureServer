package kr.co.greentech.measure.service;

import kr.co.greentech.measure.domain.MeasureFile;
import kr.co.greentech.measure.repository.FileUploadDownloadRepository;
import kr.co.greentech.measure.util.FileDownloadException;
import kr.co.greentech.measure.util.FileUploadException;
import kr.co.greentech.measure.util.FileUploadProperties;
import kr.co.greentech.measure.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadDownloadService {
    private final FileUploadProperties prop;

    @Autowired
    FileUploadDownloadRepository fileRepository;

    private Path getFilePath(FilePath path) {
        return Paths.get(prop.getUploadDir() + FileUtil.INSTANCE.getTimeString() + path).toAbsolutePath().normalize();
    }

    private Path getFilePath(FilePath path, Date date) {
        return Paths.get(prop.getUploadDir() + FileUtil.INSTANCE.getTimeString(date) + path).toAbsolutePath().normalize();
    }

    private void createFileDirectory(FilePath path) {
        Path fileLocation = getFilePath(path);
        try {
            Files.createDirectories(fileLocation);
        }catch(Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    @Autowired
    public FileUploadDownloadService(FileUploadProperties prop) {
        this.prop = prop;

        FilePath[] values = FilePath.values();
        for (int i=0; i<FilePath.values().length; i++) {
            createFileDirectory(values[i]);
        }
    }

    public List<MeasureFile> findTypeAll(String type, Long startTime, Long endTime) {
        return fileRepository.findTypeAll(type, startTime, endTime);
    }

    public void deleteFiles() {
        Date date = FileUtil.INSTANCE.deleteFiles(new Date(), prop.getUploadDir());
        fileRepository.delete(date);
    }

    public String storeFile(MultipartFile file, FilePath path) {
        String fileName = "";
        if (path == FilePath.TRIGGER || path == FilePath.SLOPE_REQUEST || path == FilePath.ACCEL_REQUEST ) {
            fileName = StringUtils.cleanPath(path.toString().replace("/", "") + FileUtil.INSTANCE.get_HH_MM_DD_String() + ".csv");
        } else {
            fileName = StringUtils.cleanPath(path.toString().replace("/", "") + FileUtil.INSTANCE.get_HH_MM_String() + ".csv");
        }

        try {
            // 파일명에 부적합 문자가 있는지 확인한다.
            if(fileName.contains(".."))
                throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);

            createFileDirectory(path);
            Path targetLocation = getFilePath(path).resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch(Exception e) {
            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
        }
    }

    @Transactional
    public void saveMeasureFile(MeasureFile file) {
        fileRepository.save(file);
    }

    public Resource loadFileAsResource(String fileName, FilePath path, Date date) {
        try {
            Path filePath = getFilePath(path, date).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()) {
                return resource;
            }else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        }catch(MalformedURLException e) {
            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
        }
    }


}
