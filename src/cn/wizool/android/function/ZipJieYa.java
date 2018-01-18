/*  1:   */ package cn.wizool.android.function;
/*  2:   */ 
/*  3:   */ import java.io.BufferedInputStream;
/*  4:   */ import java.io.BufferedOutputStream;
/*  5:   */ import java.io.File;
/*  6:   */ import java.io.FileInputStream;
/*  7:   */ import java.io.FileNotFoundException;
/*  8:   */ import java.io.FileOutputStream;
/*  9:   */ import java.io.IOException;
/* 10:   */ import java.io.PrintStream;
/* 11:   */ import java.util.zip.ZipEntry;
/* 12:   */ import java.util.zip.ZipInputStream;
/* 13:   */ 
/* 14:   */ public class ZipJieYa
/* 15:   */ {
/* 16:   */   public File decompression(String filepath, String path)
/* 17:   */   {
/* 18:23 */     long startTime = System.currentTimeMillis();
/* 19:24 */     File Fout2 = null;
/* 20:   */     try
/* 21:   */     {
/* 22:26 */       ZipInputStream Zin = new ZipInputStream(new FileInputStream(filepath));
/* 23:27 */       BufferedInputStream Bin = new BufferedInputStream(Zin);
/* 24:28 */       String Parent = path;
/* 25:29 */       File Fout = null;
/* 26:   */       try
/* 27:   */       {
/* 28:   */         ZipEntry entry;
/* 29:32 */         while (((entry = Zin.getNextEntry()) != null) && (!entry.isDirectory()))
/* 30:   */         {
/* 32:33 */           Fout = new File(Parent, entry.getName());
/* 33:34 */           if (!Fout.exists()) {
/* 34:35 */             new File(Fout.getParent()).mkdirs();
/* 35:   */           }
/* 36:37 */           FileOutputStream out = new FileOutputStream(Fout);
/* 37:38 */           BufferedOutputStream Bout = new BufferedOutputStream(out);
/* 38:   */           int b;
/* 39:40 */           while ((b = Bin.read()) != -1)
/* 40:   */           {
/* 42:41 */             Bout.write(b);
/* 43:   */           }
/* 44:43 */           Bout.close();
/* 45:44 */           out.close();
/* 46:45 */           System.out.println(Fout + "��ѹ�ɹ�");
/* 47:   */         }
/* 48:47 */         Bin.close();
/* 49:48 */         Zin.close();
/* 50:   */       }
/* 51:   */       catch (IOException e)
/* 52:   */       {
/* 53:51 */         e.printStackTrace();
/* 54:   */       }
/* 55:53 */       Fout2 = Fout;
/* 56:   */     }
/* 57:   */     catch (FileNotFoundException e)
/* 58:   */     {
/* 59:56 */       e.printStackTrace();
/* 60:   */     }
/* 61:58 */     long endTime = System.currentTimeMillis();
/* 62:59 */     System.out.println("�ķ�ʱ�䣺 " + (endTime - startTime) + " ms");
/* 63:60 */     return Fout2;
/* 64:   */   }
/* 65:   */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.function.ZipJieYa
 * JD-Core Version:    0.7.0.1
 */