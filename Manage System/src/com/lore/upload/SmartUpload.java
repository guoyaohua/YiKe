package com.lore.upload;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.lore.biz.UploadBean;
import com.lore.dao.UploadDao;

public class SmartUpload
{
    protected byte m_binArray[];
    protected HttpServletRequest m_request;
    protected HttpServletResponse m_response;
    protected ServletContext m_application;
    private int m_totalBytes;
    private int m_currentIndex;
    private int m_startData;
    private int m_endData;
    private String m_boundary;
    private long m_totalMaxFileSize;
    private long m_maxFileSize;
    private Vector m_deniedFilesList;
    private Vector m_allowedFilesList;
    private boolean m_denyPhysicalPath;

    //private boolean m_forcePhysicalPath;
    private String m_contentDisposition;
    public static final int SAVE_AUTO = 0;
    public static final int SAVE_VIRTUAL = 1;
    public static final int SAVE_PHYSICAL = 2;
    private SmartFiles m_files;
    private SmartRequest m_formRequest;

    public SmartUpload()
    {
        m_totalBytes = 0;
        m_currentIndex = 0;
        m_startData = 0;
        m_endData = 0;
        m_boundary = ""; //new String();
        m_totalMaxFileSize = 0L;
        m_maxFileSize = 0L;
        m_deniedFilesList = new Vector();
        m_allowedFilesList = new Vector();
        m_denyPhysicalPath = false;
        //m_forcePhysicalPath = false;
        m_contentDisposition = ""; //new String();
        m_files = new SmartFiles();
        m_formRequest = new SmartRequest();
    }

    /**
     * @deprecated Method init is deprecated
     */
    public final void init(ServletConfig servletconfig) throws ServletException
    {
        m_application = servletconfig.getServletContext();
    }

    /**
     * @deprecated Method service is deprecated
     */
    public void service(HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse) throws ServletException,IOException
    {
        m_request = httpservletrequest;
        m_response = httpservletresponse;
    }

    public final void initialize(ServletConfig servletconfig,HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse) throws ServletException
    {
        m_application = servletconfig.getServletContext();
        m_request = httpservletrequest;
        m_response = httpservletresponse;
    }

    public final void initialize(PageContext pagecontext) throws ServletException
    {
        m_application = pagecontext.getServletContext();
        m_request = (HttpServletRequest)pagecontext.getRequest();
        m_response = (HttpServletResponse)pagecontext.getResponse();
    }

    /**
     * @deprecated Method initialize is deprecated
     */
    public final void initialize(ServletContext servletcontext,HttpSession httpsession,HttpServletRequest httpservletrequest,HttpServletResponse httpservletresponse,JspWriter jspwriter) throws ServletException
    {
        m_application = servletcontext;
        m_request = httpservletrequest;
        m_response = httpservletresponse;
    }

    public void upload() throws ServletException,IOException,SmartUploadException
    {
        int i = 0;
        //boolean flag = false;
        boolean flag1 = false;
        //boolean flag2 = false;
        long l = 0L;
        //String s = "";//new String();
        //String s2 = "";//new String();
        String s4 = ""; //new String();
        String s5 = ""; //new String();
        String s6 = ""; //new String();
        String s7 = ""; //new String();
        String s8 = ""; //new String();
        String s9 = ""; //new String();
        String s10 = ""; //new String();
        m_totalBytes = m_request.getContentLength();
        m_binArray = new byte[m_totalBytes];
        int j;
        for(;i < m_totalBytes;i += j)
        {
            try
            {
                m_request.getInputStream();
                j = m_request.getInputStream().read(m_binArray,i,m_totalBytes - i);
            }
            catch(Exception exception)
            {
                throw new SmartUploadException("Unable to upload.");
            }
        }

        for(;!flag1 && m_currentIndex < m_totalBytes;m_currentIndex++)
        {
            if(m_binArray[m_currentIndex] == 13)
            {
                flag1 = true;
            }
            else
            {
                m_boundary = m_boundary + (char)m_binArray[m_currentIndex];

            }
        }
        if(m_currentIndex == 1)
        {
            return;
        }
        for(m_currentIndex++;m_currentIndex < m_totalBytes;m_currentIndex = m_currentIndex + 2)
        {
            String s1 = getDataHeader();
            m_currentIndex = m_currentIndex + 2;
            boolean flag3 = s1.indexOf("filename") > 0;
            String s3 = getDataFieldValue(s1,"name");
            if(flag3)
            {
                s6 = getDataFieldValue(s1,"filename");
                s4 = getFileName(s6);
                s5 = getFileExt(s4);
                s7 = getContentType(s1);
                s8 = getContentDisp(s1);
                s9 = getTypeMIME(s7);
                s10 = getSubTypeMIME(s7);
            }
            getDataSection();
            if(flag3 && s4.length() > 0)
            {
                if(m_deniedFilesList.contains(s5))
                {
                    throw new SecurityException("The extension of the file is denied to be uploaded (1015).");
                }
                if(!m_allowedFilesList.isEmpty() && !m_allowedFilesList.contains(s5))
                {
                    throw new SecurityException("The extension of the file is not allowed to be uploaded (1010).");
                }
                if(m_maxFileSize > 0L && (long)((m_endData - m_startData) + 1) > m_maxFileSize)
                {
                    throw new SecurityException("Size exceeded for this file : " + s4 + " (1105).");
                }
                l += (m_endData - m_startData) + 1;
                if(m_totalMaxFileSize > 0L && l > m_totalMaxFileSize)
                {
                    throw new SecurityException("Total File Size exceeded (1110).");
                }
            }
            if(flag3)
            {
                SmartFile file = new SmartFile();
                file.setParent(this);
                file.setFieldName(s3);
                file.setFileName(s4);
                file.setFileExt(s5);
                file.setFilePathName(s6);
                file.setIsMissing(s6.length() == 0);
                file.setContentType(s7);
                file.setContentDisp(s8);
                file.setTypeMIME(s9);
                file.setSubTypeMIME(s10);
                if(s7.indexOf("application/x-macbinary") > 0)
                {
                    m_startData = m_startData + 128;
                }
                file.setSize((m_endData - m_startData) + 1);
                file.setStartData(m_startData);
                file.setEndData(m_endData);
                m_files.addFile(file);
            }
            else
            {
            	//李欢中文乱码处理221
                //String s11 = new String(m_binArray,m_startData,(m_endData - m_startData) + 1);
                
                String s11 = new String(m_binArray,m_startData,(m_endData - m_startData) + 1, "utf-8");
                m_formRequest.putParameter(s3,s11);
            }
            if((char)m_binArray[m_currentIndex + 1] == '-')
            {
                break;
            }
        }
    }

    public int save(String s,String uploadFileName,int price,String remark,String typename,int typeId) throws ServletException,IOException,SmartUploadException
    {
        return save(s,0,uploadFileName,price,remark,typename,typeId);
    }

    
    //上传
    public int save(String s,int i,String uploadFileName,int price,String remark,String typename,int typeId) throws ServletException,IOException,SmartUploadException
    {
        int j = 0;
        if(s == null)
        {
            s = m_application.getRealPath("/");
            //System.out.println("s == null,m_application.getRealPath:" + s);
        }
        if(s.indexOf("/") != -1)
        {
            if(s.charAt(s.length() - 1) != '/')
            {
                s = s + "/";
                //System.out.println("m_application.getRealPath::" + s);
            }
        }
        else
        {
            if(s.charAt(s.length() - 1) != '\\')
            {
                s = s + "\\";
                //System.out.println("m_application.getRealPath" + s);
            }
        }
        //System.out.println("m_application.getRealPath:::" + s);
        FileNames = new String[m_files.getCount()];
        for(int k = 0;k < m_files.getCount();k++)
        {
            if(!m_files.getFile(k).isMissing())
            {
                //System.out.println("s + m_files.getFile(k).getFileName():" + s + m_files.getFile(k).getFileName());
                
            	Date date = new Date();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
            	//System.out.println("date:"+date.getTime());
            	//date.getTime()
            	//更改文件名
            	
            	
            	
            	String str= sdf.format(date)+"."+m_files.getFile(k).getFileExt();
            	m_files.getFile(k).setFileName(str);
            	m_files.getFile(k).saveAs(s + m_files.getFile(k).getFileName(),i);
                
                
                
                FileNames[j] = s + m_files.getFile(k).getFileName();
                j++;
                
                
                UploadDao dao = new UploadDao();
            	UploadBean bean = new UploadBean();
            	
            	bean.setFileName(uploadFileName);
            	bean.setFileExt(m_files.getFile(k).getFileExt());
            	bean.setFilePath("/upload/"+str);
            	bean.setPrice(price);
            	bean.setRemark(remark);
            	bean.setTypeId(typeId);
            	bean.setTypename(typename);
            	 
            	 
            	dao.insert(bean);
            }
        }

        return j;
    }

    //Add
    private String[] FileNames;
    public String[] getFileNames()
    {
        //Method may expose internal representation by returning array
        //Returning an array value stored in one of the object's fields exposes the internal representation of the object.? For classes shared by other untrusted classes, this could potentially be a security issue.? Returning a new copy of the array is better approach in many situations.
        String[] vFileNames = new String[FileNames.length];
        System.arraycopy(FileNames,0,vFileNames,0,FileNames.length);
        return vFileNames;
    }

    public int getSize()
    {
        return m_totalBytes;
    }

    public byte getBinaryData(int i)
    {
        byte byte0;
        try
        {
            byte0 = m_binArray[i];
        }
        catch(Exception exception)
        {
            throw new ArrayIndexOutOfBoundsException("Index out of range (1005).");
        }
        return byte0;
    }

    public SmartFiles getFiles()
    {
        return m_files;
    }

    public SmartRequest getRequest()
    {
        return m_formRequest;
    }

    public void downloadFile(String s) throws ServletException,IOException,SmartUploadException
    {
        downloadFile(s,null,null);
    }

    public void downloadFile(String s,String s1) throws ServletException,IOException,SmartUploadException,SmartUploadException
    {
        downloadFile(s,s1,null);
    }

    public void downloadFile(String s,String s1,String s2) throws ServletException,IOException,SmartUploadException
    {
        downloadFile(s,s1,s2,65000);
    }

    public void downloadFile(String s, String s1, String s2, int i)
        throws ServletException, IOException, SmartUploadException
    {
        if(s == null)
            throw new IllegalArgumentException("File '" + s +
            "' not found (1040).");
        if(s.equals(""))
            throw new IllegalArgumentException("File '" + s +
            "' not found (1040).");
        if(!isVirtual(s) && m_denyPhysicalPath)
            throw new SecurityException("Physical path is denied (1035).");
        if(isVirtual(s))
            s = m_application.getRealPath(s);
        java.io.File file = new java.io.File(s);
        FileInputStream fileinputstream = new FileInputStream(file);
        long l = file.length();
        boolean flag = false;
        int k = 0;
        byte abyte0[] = new byte[i];
        if(s1 == null)
            m_response.setContentType("application/x-msdownload");
        else
        if(s1.length() == 0)
            m_response.setContentType("application/x-msdownload");
        else
            m_response.setContentType(s1);
        m_response.setContentLength((int)l);
        m_contentDisposition = m_contentDisposition != null ?
        m_contentDisposition : "attachment;";
        if(s2 == null)
            m_response.setHeader("Content-Disposition",
            m_contentDisposition + " filename=" +
            toUtf8String(getFileName(s)));
        else
        if(s2.length() == 0)
            m_response.setHeader("Content-Disposition",
            m_contentDisposition);
        else
            m_response.setHeader("Content-Disposition",
            m_contentDisposition + " filename=" + toUtf8String(s2));
        while((long)k < l)
        {
            int j = fileinputstream.read(abyte0, 0, i);
            k += j;
            m_response.getOutputStream().write(abyte0, 0, j);
        }
        fileinputstream.close();
    }

    /**
     * ���ļ����еĺ���תΪUTF8����Ĵ�,�Ա�����ʱ����ȷ��ʾ�����ļ���.
     * �ݺ������������������2003.08.01
     * @param s ԭ�ļ���
     * @return ���±������ļ���
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    sb.append("%" + Integer.toHexString(k).
                    toUpperCase());
                }
            }
        }
        return sb.toString();
    }





    public void downloadField(ResultSet resultset,String s,String s1,String s2) throws ServletException,IOException,SQLException
    {
        if(resultset == null)
        {
            throw new IllegalArgumentException("The RecordSet cannot be null (1045).");
        }
        if(s == null)
        {
            throw new IllegalArgumentException("The columnName cannot be null (1050).");
        }
        if(s.length() == 0)
        {
            throw new IllegalArgumentException("The columnName cannot be empty (1055).");
        }
        byte abyte0[] = resultset.getBytes(s);
        if(s1 == null)
        {
            m_response.setContentType("application/x-msdownload");
        }
        else
        {
            if(s1.length() == 0)
            {
                m_response.setContentType("application/x-msdownload");
            }
            else
            {
                m_response.setContentType(s1);
            }
        }
        m_response.setContentLength(abyte0.length);
        if(s2 == null)
        {
            m_response.setHeader("Content-Disposition","attachment;");
        }
        else
        {
            if(s2.length() == 0)
            {
                m_response.setHeader("Content-Disposition","attachment;");
            }
            else
            {
                m_response.setHeader("Content-Disposition","attachment; filename=" + s2);
            }
        }
        m_response.getOutputStream().write(abyte0,0,abyte0.length);
    }

    public void fieldToFile(ResultSet resultset,String s,String s1) throws ServletException,IOException,SmartUploadException,SQLException
    {
        try
        {
            if(m_application.getRealPath(s1) != null)
            {
                s1 = m_application.getRealPath(s1);
            }
            InputStream inputstream = resultset.getBinaryStream(s);
            FileOutputStream fileoutputstream = new FileOutputStream(s1);
            int i;
            while((i = inputstream.read()) != -1)
            {
                fileoutputstream.write(i);
            }
            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            throw new SmartUploadException("Unable to save file from the DataBase (1020).");
        }
    }

    private String getDataFieldValue(String s,String s1)
    {
        String s2 = ""; // = new String();
        String s3 = ""; // = new String();
        int i = 0;
        //boolean flag = false;
        //boolean flag1 = false;
        //boolean flag2 = false;
        s2 = s1 + "=" + '"';
        i = s.indexOf(s2);
        if(i > 0)
        {
            int j = i + s2.length();
            int k = j;
            s2 = "\"";
            int l = s.indexOf(s2,j);
            if(k > 0 && l > 0)
            {
                s3 = s.substring(k,l);
            }
        }
        return s3;
    }

    private String getFileExt(String s)
    {
        String s1; // = new String();
        int i = 0;
        int j = 0;
        if(s == null)
        {
            return null;
        }
        i = s.lastIndexOf('.') + 1;
        j = s.length();
        s1 = s.substring(i,j);
        if(s.lastIndexOf('.') > 0)
        {
            return s1;
        }
        else
        {
            return "";
        }
    }

    private String getContentType(String s)
    {
        String s1 = ""; // = new String();
        String s2 = ""; // = new String();
        int i = 0;
        //boolean flag = false;
        s1 = "Content-Type:";
        i = s.indexOf(s1) + s1.length();
        if(i != -1)
        {
            int j = s.length();
            s2 = s.substring(i,j);
        }
        return s2;
    }

    private String getTypeMIME(String s)
    {
        //String s1 = new String();
        int i = 0;
        i = s.indexOf("/");
        if(i != -1)
        {
            return s.substring(1,i);
        }
        else
        {
            return s;
        }
    }

    private String getSubTypeMIME(String s)
    {
        //String s1 = new String();
        //boolean flag = false;
        int i = 0;
        i = s.indexOf("/") + 1;
        if(i != -1)
        {
            int j = s.length();
            return s.substring(i,j);
        }
        else
        {
            return s;
        }
    }

    private String getContentDisp(String s)
    {
        //String s1 = new String();
        String s1 = "";
        int i = 0;
        int j = 0;
        i = s.indexOf(":") + 1;
        j = s.indexOf(";");
        s1 = s.substring(i,j);
        return s1;
    }

    private void getDataSection()
    {
        //boolean flag = false;
        //String s = "";
        //String s = new String();
        int i = m_currentIndex;
        int j = 0;
        int k = m_boundary.length();
        m_startData = m_currentIndex;
        m_endData = 0;
        while(i < m_totalBytes)
        {
            if(m_binArray[i] == (byte)m_boundary.charAt(j))
            {
                if(j == k - 1)
                {
                    m_endData = ((i - k) + 1) - 3;
                    break;
                }
                i++;
                j++;
            }
            else
            {
                i++;
                j = 0;
            }
        }
        m_currentIndex = m_endData + k + 3;
    }

    private String getDataHeader()
    {
        //boolean flag = false;
        int i = m_currentIndex;
        int j = 0;
        for(boolean flag1 = false;!flag1;)
        {
            if(m_binArray[m_currentIndex] == 13 && m_binArray[m_currentIndex + 2] == 13)
            {
                flag1 = true;
                j = m_currentIndex - 1;
                m_currentIndex = m_currentIndex + 2;
            }
            else
            {
                m_currentIndex++;
            }
        }
        //李欢中文乱码处理676---682
        //String s = new String(m_binArray,i,(j - i) + 1);
        //return s;
        String s = null;
        try {
             s = new String(m_binArray,i,(j - i) + 1, "utf-8");
        } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
        }
        return s;
        
        
    }

    private String getFileName(String s)
    {
        //String s1 = ""; // = new String();
        //String s2 = ""; // = new String();
        //boolean flag = false;
        //boolean flag1 = false;
        //boolean flag2 = false;
        int i = 0;
        i = s.lastIndexOf('/');
        if(i != -1)
        {
            return s.substring(i + 1,s.length());
        }
        i = s.lastIndexOf('\\');
        if(i != -1)
        {
            return s.substring(i + 1,s.length());
        }
        else
        {
            return s;
        }
    }

    public void setDeniedFilesList(String s) throws ServletException,IOException,SQLException
    {
        //String s1 = "";
        if(s != null)
        {
            String s2 = "";
            for(int i = 0;i < s.length();i++)
            {
                if(s.charAt(i) == ',')
                {
                    if(!m_deniedFilesList.contains(s2))
                    {
                        m_deniedFilesList.addElement(s2);
                    }
                    s2 = "";
                }
                else
                {
                    s2 = s2 + s.charAt(i);
                }
            }

            //if(s2 != "")
            if(!s2.equals(""))
            {
                m_deniedFilesList.addElement(s2);
            }
        }
        else
        {
            m_deniedFilesList = null;
        }
    }

    public void setAllowedFilesList(String s)
    {
        //String s1 = "";
        if(s != null)
        {
            String s2 = "";
            for(int i = 0;i < s.length();i++)
            {
                if(s.charAt(i) == ',')
                {
                    if(!m_allowedFilesList.contains(s2))
                    {
                        m_allowedFilesList.addElement(s2);
                    }
                    s2 = "";
                }
                else
                {
                    s2 = s2 + s.charAt(i);
                }
            }
            //if(s2 != "")
            if(!s2.equals(""))
            {
                m_allowedFilesList.addElement(s2);
            }
        }
        else
        {
            m_allowedFilesList = null;
        }
    }

    public void setDenyPhysicalPath(boolean flag)
    {
        m_denyPhysicalPath = flag;
    }

    public void setForcePhysicalPath(boolean flag)
    {
        //m_forcePhysicalPath = flag;
    }

    public void setContentDisposition(String s)
    {
        m_contentDisposition = s;
    }

    public void setTotalMaxFileSize(long l)
    {
        m_totalMaxFileSize = l;
    }

    public void setMaxFileSize(long l)
    {
        m_maxFileSize = l;
    }

    protected String getPhysicalPath(String s,int i) throws IOException
    {
        String s1 = ""; //new String();
        String s2 = ""; //new String();
        String s3 = ""; //new String();
        boolean flag = false;
        s3 = System.getProperty("file.separator");
        if(s == null)
        {
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        }
        if(s.equals(""))
        {
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        }
        if(s.lastIndexOf("\\") >= 0)
        {
            s1 = s.substring(0,s.lastIndexOf("\\"));
            s2 = s.substring(s.lastIndexOf("\\") + 1);
        }
        if(s.lastIndexOf("/") >= 0)
        {
            s1 = s.substring(0,s.lastIndexOf("/"));
            s2 = s.substring(s.lastIndexOf("/") + 1);
        }
        s1 = s1.length() != 0 ? s1 : "/";
        java.io.File file = new java.io.File(s1);
        if(file.exists())
        {
            flag = true;
        }
        if(i == 0)
        {
            if(isVirtual(s1))
            {
                s1 = m_application.getRealPath(s1);
                if(s1.endsWith(s3))
                {
                    s1 = s1 + s2;
                }
                else
                {
                    s1 = s1 + s3 + s2;
                }
                return s1;
            }
            if(flag)
            {
                if(m_denyPhysicalPath)
                {
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                }
                else
                {
                    return s;
                }
            }
            else
            {
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
        }
        if(i == 1)
        {
            if(isVirtual(s1))
            {
                s1 = m_application.getRealPath(s1);
                if(s1.endsWith(s3))
                {
                    s1 = s1 + s2;
                }
                else
                {
                    s1 = s1 + s3 + s2;
                }
                return s1;
            }
            if(flag)
            {
                throw new IllegalArgumentException("The path is not a virtual path.");
            }
            else
            {
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
        }
        if(i == 2)
        {
            if(flag)
            {
                if(m_denyPhysicalPath)
                {
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                }
                else
                {
                    return s;
                }
            }
            if(isVirtual(s1))
            {
                throw new IllegalArgumentException("The path is not a physical path.");
            }
            else
            {
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
        }
        else
        {
            return null;
        }
    }

    public void uploadInFile(String s) throws IOException,SmartUploadException
    {
        //boolean flag = false;
        int i = 0;
        int j = 0;
        if(s == null)
        {
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        }
        if(s.length() == 0)
        {
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        }
        if(!isVirtual(s) && m_denyPhysicalPath)
        {
            throw new SecurityException("Physical path is denied (1035).");
        }
        i = m_request.getContentLength();
        m_binArray = new byte[i];
        int k;
        for(;j < i;j += k)
        {
            try
            {
                k = m_request.getInputStream().read(m_binArray,j,i - j);
            }
            catch(Exception exception)
            {
                throw new SmartUploadException("Unable to upload.");
            }
        }

        if(isVirtual(s))
        {
            s = m_application.getRealPath(s);
        }
        try
        {
            java.io.File file = new java.io.File(s);
            FileOutputStream fileoutputstream = new FileOutputStream(file);
            fileoutputstream.write(m_binArray);
            fileoutputstream.close();
        }
        catch(Exception exception1)
        {
            throw new SmartUploadException("The Form cannot be saved in the specified file (1030).");
        }
    }

    private boolean isVirtual(String s)
    {
        if(m_application.getRealPath(s) != null)
        {
            java.io.File file = new java.io.File(m_application.getRealPath(s));
            return file.exists();
        }
        else
        {
            return false;
        }
    }
}
