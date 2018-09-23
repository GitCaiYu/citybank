package com.tansun.citybank.util;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tansun.citybank.dao.BaseDao;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class FileUtil {

    //�ļ�����
    public static final String FILE_EXPORT = "FILE_EXPORT";
    //�ļ�����
    public static final String FILE_IMPORT = "FILE_IMPORT";
    @Autowired
    private BaseDao baseDao;

    /**
     * �������
     * �����߳�����coreSize
     * ����������count
     * �ļ�·�����ļ�����file
     * ����򵼳���ʶ��type
     */
    public void FileExport(int coreSize, int count, String file, Map parameters, String type,Class object) {
        for (int i = 1; i <= coreSize; i++) {
            //Mybatis��ҳ����
            RowBounds rowBounds = new RowBounds((i - 1) * count / coreSize, count / coreSize);
            //�˴�ΪҪд���ļ��ļ���
            List<Object> list = baseDao.selectPage(rowBounds, null);
            new Thread(new Handle(file, list, type)).start();
        }
    }
}

/**
 * ���̴߳����ڲ���
 */
class Handle implements Runnable {

    //�ļ�·�����ļ���
    private String file;

    //Ҫд���ļ��ļ���
    private List list;

    //�ļ�����ʽ
    private String type;

    public Handle(String file, List list, String type) {
        this.file = file;
        this.list = list;
        this.type = type;
    }

    @Override
    public void run() {
        if (type.equals(FileUtil.FILE_EXPORT)) {
            FileExport();
        }
        if (type.equals(FileUtil.FILE_IMPORT)) {
            FileImport();
        }
    }

    /**
     * �ļ�����
     */
    public void FileExport() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            list.forEach(file -> {
                try {
                    fileWriter.append(file + "\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ļ�����
     */
    public void FileImport() {

    }

}