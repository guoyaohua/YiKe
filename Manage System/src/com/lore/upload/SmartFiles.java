package com.lore.upload;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

public class SmartFiles
{
    //private SmartUpload m_parent;
    private Hashtable m_files;
    private int m_counter;

    SmartFiles()
    {
        m_files = new Hashtable();
        m_counter = 0;
    }

    protected void addFile(SmartFile file)
    {
        if(file == null)
        {
            throw new IllegalArgumentException("newFile cannot be null.");
        }
        else
        {
            m_files.put(new Integer(m_counter),file);
            m_counter++;
            return;
        }
    }

    public SmartFile getFile(int i)
    {
        if(i < 0)
        {
            throw new IllegalArgumentException("File's index cannot be a negative value (1210).");
        }
        SmartFile file = (SmartFile)m_files.get(new Integer(i));
        if(file == null)
        {
            throw new IllegalArgumentException("Files' name is invalid or does not exist (1205).");
        }
        else
        {
            return file;
        }
    }

    public int getCount()
    {
        return m_counter;
    }

    public long getSize() throws IOException
    {
        long l = 0L;
        for(int i = 0;i < m_counter;i++)
        {
            l += getFile(i).getSize();
        }
        return l;
    }

    public Collection getCollection()
    {
        return m_files.values();
    }

    public Enumeration getEnumeration()
    {
        return m_files.elements();
    }
}
