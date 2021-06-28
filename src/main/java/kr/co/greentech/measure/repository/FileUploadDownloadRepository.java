package kr.co.greentech.measure.repository;

import kr.co.greentech.measure.domain.MeasureFile;
import kr.co.greentech.measure.util.FileUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class FileUploadDownloadRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(MeasureFile file) {
        Date date = new Date(file.getTime());

        List<MeasureFile> items = findByURL(
                file.getUrl(),
                FileUtil.INSTANCE.startOfDay(date).getTime(),
                FileUtil.INSTANCE.endOfDay(date).getTime()
        );

        if (items.isEmpty()) {
            System.out.println("MeasureFile 저장");
            em.persist(file);
        }
    }

    private List<MeasureFile> findByURL(String url, Long startTime, Long endTime) {
        return em
                .createQuery(
                        "select mf from MeasureFile mf " +
                                "where mf.url = :url " +
                                "and mf.time >= :startTime " +
                                "and mf.time <= :endTime ",
                        MeasureFile.class
                ).setParameter("url", url)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getResultList();
    }

    public List<MeasureFile> findTypeAll(String type, Long startTime, Long endTime) {

        List<MeasureFile> basicList = em
                .createQuery(
                        "select mf from MeasureFile mf " +
                                "where mf.type = :type " +
                                "and mf.time >= :startTime " +
                                "and mf.time <= :endTime ",
                        MeasureFile.class
                ).setParameter("type", type)
                .setParameter("startTime", startTime * 1000)
                .setParameter("endTime", endTime * 1000)
                .getResultList();

        List<MeasureFile> javaList = em
                .createQuery(
                        "select mf from MeasureFile mf " +
                                "where mf.type = :type " +
                                "and mf.time >= :startTime " +
                                "and mf.time <= :endTime ",
                        MeasureFile.class
                ).setParameter("type", type)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getResultList();

        basicList.addAll(javaList);
        return basicList;
    }
}
