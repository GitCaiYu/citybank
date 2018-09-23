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

    //文件导出
    public static final String FILE_EXPORT = "FILE_EXPORT";
    //文件导入
    public static final String FILE_IMPORT = "FILE_IMPORT";
    @Autowired
    private BaseDao baseDao;

    /**
     * 结果导出
     * 核心线程数：coreSize
     * 数据总量：count
     * 文件路径和文件名：file
     * 导入或导出标识：type
     */
    public void FileExport(int coreSize, int count, String file, Map parameters, String type,Class object) {
        for (int i = 1; i <= coreSize; i++) {
            //Mybatis分页参数
            RowBounds rowBounds = new RowBounds((i - 1) * count / coreSize, count / coreSize);
            //此处为要写入文件的集合
            List<Object> list = baseDao.selectPage(rowBounds, null);
            new Thread(new Handle(file, list, type)).start();
        }
    }
}

/**
 * 多线程处理内部类
 */
class Handle implements Runnable {

    //文件路径和文件名
    private String file;

    //要写入文件的集合
    private List list;

    //文件处理方式
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
     * 文件导出
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
     * 文件导入
     */
    public void FileImport() {

    }

}