package api.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "getAllData")
    public Object[][] getAllData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\testdata\\" + "userdatatest.xlsx";
        XLUtils xlUtils = new XLUtils(filePath);
        Object[][] apidata = null;
        int lastRowNum = xlUtils.getRowCount("Sheet1");
        int lastCellNum = xlUtils.getCellCount("Sheet1", 1);
        apidata = new Object[lastRowNum][lastCellNum];
        for (int i = 1; i <= lastRowNum; i++) {
            for (int j = 0; j < lastCellNum; j++) {
                apidata[i - 1][j] = xlUtils.getCellData("Sheet1", i, j);
            }
        }
        return apidata;
    }
    @DataProvider(name = "getUserNames")
    public Object[] getUserNames() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\testdata\\" + "userdatatest.xlsx";
        XLUtils xlUtils = new XLUtils(filePath);
        Object[] apidata = null;
        int lastRowNum = xlUtils.getRowCount("Sheet1");
        apidata = new Object[lastRowNum];
        for (int i = 1; i <= lastRowNum; i++) {
                apidata[i - 1] = xlUtils.getCellData("Sheet1", i, 1);
        }
        return apidata;
    }

    @Test(dataProvider = "getAllData")
    public void testWithData(String... data) {
        for (String element : data) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

}


