package Test;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;
 
public class Window extends JFrame {
	public JTextPane textPane = new JTextPane(); //文本窗格d，编辑窗口
	public JFileChooser filechooser = new JFileChooser(); //文件选择器
 
	public Window()
	{
		super("日志分析器"); 
		
		Action[] actions=			    //菜单项的各种功能
			{					
					new OpenAction(),	//打开				
					
					new SplitAction(),  //分割
					
					new AnalystsAction(),  //高频词
					
					new ExitAction()    //退出
			};
		setJMenuBar(createJMenuBar(actions));		//根据actions创建菜单栏
		Container container=getContentPane();		
		container.add(textPane, BorderLayout.CENTER);
		setLocation(700,100);
		setSize(500,800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JMenuBar createJMenuBar(Action[] actions)	//创建菜单栏的函数
	{
		JMenuBar menubar=new JMenuBar();
		JMenu menuFile=new JMenu("功能区");
		
		menuFile.add(new JMenuItem(actions[0]));
		menuFile.add(new JMenuItem(actions[1]));
		menuFile.add(new JMenuItem(actions[2]));
		menuFile.add(new JMenuItem(actions[3]));

		menubar.add(menuFile);
		
		return menubar;
	}
  
	class OpenAction extends AbstractAction		//打开
	{
		public OpenAction()
		{
			super("打开文件");
		}
		public void actionPerformed(ActionEvent e)
		{
			int i=filechooser.showOpenDialog(Window.this);			//显示打开文件对话框
			if(i==JFileChooser.APPROVE_OPTION)			//点击对话框打开选项
			{
				File f=filechooser.getSelectedFile();	//得到选择的文件
				try
				{
					textPane.setText(null);
					InputStream is=new FileInputStream(f);
					InputStreamReader isr=new InputStreamReader(is);
					BufferedReader br=new BufferedReader(isr);
					textPane.read(br, "d");
					isr.close();
				}
				
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	class ExitAction extends AbstractAction		//退出
	{
		public ExitAction()
		{
			super("退出");
		}
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	}
	class AnalystsAction extends AbstractAction		//退出
	{
		public AnalystsAction()
		{
			super("高频词统计");
		}
		public void actionPerformed(ActionEvent e)
		{   JTextField text1=new JTextField();
			int i=filechooser.showOpenDialog(Window.this);			//显示打开文件对话框
			if(i==JFileChooser.APPROVE_OPTION)			//点击对话框打开选项
			{
				File file=filechooser.getSelectedFile();	//得到选择的文件
				try
				{
					TreeSet<WordBean> set =new TreeSet<WordBean>();
					set=new CountWord().countLetter(file, 20);
					
					//输出set中的数据
							
					Iterator ite =set.iterator();
					String [] str=new String [20];
					int [] str1=new int [20];
					int count=0;
					int j =0;
					while(ite.hasNext()){
						if(count++<20){
							WordBean bean =(WordBean) ite.next();	
							str[j]=bean.getKey();							
							str1[j]=bean.getCount();	
							j++;
							
						}else{
							
							break;
						}
					}	
					//遍历三：
					textPane.setText(null);
					for(int i1=0;i1<20;i1++){					
					textPane.setText(textPane.getText()+str[i1]+":"+str1[i1]);
					textPane.setText(textPane.getText()+"\n");
					}								
					    }
				
				catch(Exception ex)
				{   
					
					ex.printStackTrace();
				}
			}
		}
	}
	
	class SplitAction extends AbstractAction		//分割
	{
		public SplitAction()
		{
			super("文件分割");
		}
		public void actionPerformed(ActionEvent e)
		{   int SIZE = 1024 * 1024;
			int i=filechooser.showOpenDialog(Window.this);			//显示打开文件对话框
			if(i==JFileChooser.APPROVE_OPTION)			//点击对话框打开选项
			{
				File file=filechooser.getSelectedFile();	//得到选择的文件
				try
				{
					new SplitFile().splitFile(file);
					JOptionPane.showMessageDialog(null, "分割成功!分割后文件存放在E:/Test/SpilitFlie下");			 
					    }
				
				catch(Exception ex)
				{   
					JOptionPane.showMessageDialog(null, "分割失败!");
					ex.printStackTrace();
				}
			}
		}
	}
		
	public static void main(String[] args)
	{
		new Window();
	}
}