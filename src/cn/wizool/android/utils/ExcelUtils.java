/*   1:    */ package cn.wizool.android.utils;
/*   2:    */ 
/*   3:    */ import java.io.FileInputStream;
/*   4:    */ import java.io.FileNotFoundException;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.InputStream;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.util.Date;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.Map;
/*  11:    */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*  12:    */ import org.apache.poi.ss.usermodel.Cell;
/*  13:    */ import org.apache.poi.ss.usermodel.DateUtil;
/*  14:    */ import org.apache.poi.ss.usermodel.RichTextString;
/*  15:    */ import org.apache.poi.ss.usermodel.Row;
/*  16:    */ import org.apache.poi.ss.usermodel.Sheet;
/*  17:    */ import org.apache.poi.ss.usermodel.Workbook;
/*  18:    */ import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*  19:    */ import org.slf4j.Logger;
/*  20:    */ import org.slf4j.LoggerFactory;
/*  21:    */ 
/*  22:    */ public class ExcelUtils
/*  23:    */ {
/*  24: 27 */   private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
/*  25:    */   private static Workbook wb;
/*  26:    */   private static Sheet sheet;
/*  27:    */   private static Row row;
/*  28:    */   
/*  29:    */   public static Workbook ReadExcelUtils(String filepath)
/*  30:    */   {
/*  31: 33 */     if (filepath == null) {
/*  32: 34 */       return wb;
/*  33:    */     }
/*  34: 36 */     String ext = filepath.substring(filepath.lastIndexOf("."));
/*  35:    */     try
/*  36:    */     {
/*  37: 38 */       InputStream is = new FileInputStream(filepath);
/*  38: 39 */       if (".xls".equals(ext)) {
/*  39: 40 */         wb = new HSSFWorkbook(is);
/*  40: 41 */       } else if (".xlsx".equals(ext)) {
/*  41: 42 */         wb = new XSSFWorkbook(is);
/*  42:    */       } else {
/*  43: 44 */         wb = null;
/*  44:    */       }
/*  45: 46 */       is.close();
/*  46:    */     }
/*  47:    */     catch (FileNotFoundException e)
/*  48:    */     {
/*  49: 48 */       logger.error("FileNotFoundException", e);
/*  50:    */     }
/*  51:    */     catch (IOException e)
/*  52:    */     {
/*  53: 50 */       logger.error("IOException", e);
/*  54:    */     }
/*  55: 52 */     return wb;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public String[] readExcelTitle(Workbook wb)
/*  59:    */     throws Exception
/*  60:    */   {
/*  61: 57 */     if (wb == null) {
/*  62: 58 */       throw new Exception("Workbook对象为空！");
/*  63:    */     }
/*  64: 60 */     sheet = wb.getSheetAt(0);
/*  65: 61 */     row = sheet.getRow(0);
/*  66:    */     
/*  67: 63 */     int colNum = row.getPhysicalNumberOfCells();
/*  68: 64 */     System.out.println("colNum:" + colNum);
/*  69: 65 */     String[] title = new String[colNum];
/*  70: 66 */     for (int i = 0; i < colNum; i++) {
/*  71: 69 */       title[i] = row.getCell(i).getStringCellValue();
/*  72:    */     }
/*  73: 71 */     return title;
/*  74:    */   }
/*  75:    */   
/*  76:    */   private static Object getCellFormatValue(Cell cell)
/*  77:    */   {
/*  78: 75 */     Object cellvalue = "";
/*  79: 76 */     if (cell != null) {
/*  80: 78 */       switch (cell.getCellType())
/*  81:    */       {
/*  82:    */       case 0: 
/*  83:    */       case 2: 
/*  84: 82 */         if (DateUtil.isCellDateFormatted(cell))
/*  85:    */         {
/*  86: 87 */           Date date = cell.getDateCellValue();
/*  87: 88 */           cellvalue = date;
/*  88:    */         }
/*  89:    */         else
/*  90:    */         {
/*  91: 92 */           cellvalue = String.valueOf(cell.getNumericCellValue());
/*  92:    */         }
/*  93: 94 */         break;
/*  94:    */       case 1: 
/*  95: 98 */         cellvalue = cell.getRichStringCellValue().getString();
/*  96: 99 */         break;
/*  97:    */       default: 
/*  98:101 */         cellvalue = "";
/*  99:    */         
/* 100:103 */         break;
/* 101:    */       }
/* 102:    */     } else {
/* 103:104 */       cellvalue = "";
/* 104:    */     }
/* 105:106 */     return cellvalue;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public static Map<Integer, Map<Integer, Object>> readExcelContent(Workbook wb, int s)
/* 109:    */     throws Exception
/* 110:    */   {
/* 111:110 */     if (wb == null) {
/* 112:111 */       throw new Exception("Workbook对象为空！");
/* 113:    */     }
/* 114:113 */     Map<Integer, Map<Integer, Object>> content = new HashMap();
/* 115:    */     
/* 116:115 */     sheet = wb.getSheetAt(s);
/* 117:    */     
/* 118:117 */     int rowNum = sheet.getLastRowNum();
/* 119:    */     
/* 120:119 */     row = sheet.getRow(0);
/* 121:120 */     int colNum = row.getPhysicalNumberOfCells();
/* 122:122 */     for (int i = 1; i <= rowNum; i++)
/* 123:    */     {
/* 124:123 */       row = sheet.getRow(i);
/* 125:124 */       int j = 0;
/* 126:125 */       Map<Integer, Object> cellValue = new HashMap();
/* 127:126 */       while (j < colNum)
/* 128:    */       {
/* 129:127 */         Object obj = getCellFormatValue(row.getCell(j));
/* 130:128 */         cellValue.put(Integer.valueOf(j), obj);
/* 131:129 */         j++;
/* 132:    */       }
/* 133:131 */       content.put(Integer.valueOf(i), cellValue);
/* 134:    */     }
/* 135:133 */     return content;
/* 136:    */   }
/* 137:    */ }


/* Location:           E:\WizoolOA\apache-tomcat-7.0.57\webapps\obpm\WEB-INF\classes\
 * Qualified Name:     cn.wizool.android.utils.ExcelUtils
 * JD-Core Version:    0.7.0.1
 */