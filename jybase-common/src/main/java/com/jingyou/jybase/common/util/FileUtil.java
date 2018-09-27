package com.jingyou.jybase.common.util;

import java.io.File;

/**
 * Created by Administrator on 2017/1/17.
 */
public class FileUtil {

    public static void main(String[] args) {
       boolean result = deleteDirectory("C:\\Users\\Administrator\\CIC_HOME\\collect_dir\\201701");
        System.out.println(result);
    }
    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean deleteFile(File file) {
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   dirPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirPath) {
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
       return deleteDirectory(dirFile);
    }

    public static boolean deleteDirectory(File dirFile) {
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        File[] files = dirFile.listFiles();
        boolean flag = false;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath());
            }
        }
        return flag && dirFile.delete();
    }
}
