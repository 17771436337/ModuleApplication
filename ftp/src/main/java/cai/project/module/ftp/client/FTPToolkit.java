package cai.project.module.ftp.client;

import android.util.Log;

import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * FTP客户端工具
 *
 */
public class FTPToolkit {
    private FTPClient mFtpClient =null;

    public FTPToolkit(){
        mFtpClient=new FTPClient();
        this.mFtpClient.setConnectTimeout(10*1000);
    }


    /**
     * 设置客户端
     * @param mFtpClient
     */
    public void setFtpClient(FTPClient mFtpClient) {
        this.mFtpClient = mFtpClient;
    }

    /**
     *压缩传输传输
     */
    public void useCompressedTransfer (){
        try {
            mFtpClient.setFileTransferMode(org.apache.commons.net.ftp.FTP.COMPRESSED_TRANSFER_MODE);
        }catch (Exception e){
            e.printStackTrace();
        };
    }

    /**列表名称*/
    public String [] listName () throws Exception {
        try{
            return mFtpClient.listNames();
        }catch (Exception e){
            throw e;
        }
    }

    public boolean setWorkingDirectory (String dir)throws  Exception{
        try{
            return mFtpClient.changeWorkingDirectory(dir);
        }catch (Exception e){
            throw e;
        }
    }

    public FTPClient getFtpClient() {return mFtpClient;}


    /**
     * 设置重连时间
     * @param seconds
     *      时间（单位秒）
     * @throws Exception
     */
    public void setTimeout (int seconds) throws  Exception{
        try {
            mFtpClient.setConnectTimeout(seconds * 1000);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 访问文件夹
     * @param dir
     *      文件夹名称
     * @return
     * @throws Exception
     */
    public boolean makeDir(String dir) throws Exception {
        try {
            return mFtpClient.makeDirectory(dir);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 断开连接
     */
    public void disconnect(){
        try {
            mFtpClient.disconnect();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    /**
     * 连接FTP
     * @param ip
     *      对应的ip地址
     * @param userName
     *      用户名
     * @param pass
     *      密码
     * @throws Exception
     */
    public void connect(String ip,int port, String userName, String pass) throws Exception{
        boolean status = false;
        try {
            try {
                mFtpClient.connect(ip,port);
            }catch (Exception e){
                e.printStackTrace();
            }
            status = mFtpClient.login(userName, pass);
            Log.e("isEasyFTPConnected", String.valueOf(status));
        } catch (SocketException e) {
            throw e;
        }
        catch (UnknownHostException e) {
            throw e;
        }
        catch (IOException e) {
            throw e;
        }
    }

    //Passing Local File path/Uri
    public void uploadFile(String uri,String name) throws  Exception{
        try {
            File file = new File(uri);
            mFtpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            FileInputStream srcFileStream = new FileInputStream(file);
            boolean status = mFtpClient.storeFile(name, srcFileStream);
            Log.e("Status", String.valueOf(status));
            srcFileStream.close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 传递InputStream和文件名
     * @param srcFileStream
     *          输入流
     * @param name
     *          文件名
     * @throws Exception
     */
    public void uploadFile(InputStream srcFileStream, String name) throws  Exception{
        try {
            mFtpClient.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
            boolean status = mFtpClient.storeFile(name, srcFileStream);
            Log.e("Status", String.valueOf(status));
            srcFileStream.close();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 下载文件
     * @param remoteFilePath
     *          文件对应的地址
     * @param dest
     *          文件名称（需要后缀）
     * @throws Exception
     */
    public void downloadFile(String remoteFilePath, String dest)  throws Exception{
        File downloadFile=new File(dest);
        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists())
            parentDir.mkdir();
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
            mFtpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            boolean status= mFtpClient.retrieveFile(remoteFilePath, outputStream);
            Log.e("Status", String.valueOf(status));
        } catch (Exception e) {
            throw e;
        }
        finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
}
