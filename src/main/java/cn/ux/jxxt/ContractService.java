//package cn.ux.jxxt;
//
//import cn.ux.jxxt.dao.ContractDao;
//import cn.ux.jxxt.domain.ContractData;
//import cn.ux.jxxt.util.TextUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.NumberFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContractService {
//
//    public static void main(String[] args) {
//        List<ContractData> list2007 = getAllByExcel();
//        for (ContractData employee : list2007) {
//            System.out.println(employee.getContractNo() + "---" + employee.getContractAddTime() + "----" + employee.getContractUserPhone());
//        }
//    }
//}
