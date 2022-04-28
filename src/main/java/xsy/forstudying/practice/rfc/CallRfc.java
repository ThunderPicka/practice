package xsy.forstudying.practice.rfc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import org.springframework.stereotype.Service;


/**
 * @Description: rfc调用接口
 * @author jiangzl
 * @date 2020-12-21
 * @ClassName: CallRfc
 *
 */
@Service
public class CallRfc
{
    public List<Map<String,Object>> CallSpreadRfc(Map<String,Object> paramMap, List<Map<String, Object>> paramList) {
        List<Map<String,Object>> list = new ArrayList<>();
        // 获取RFC 对象
        JCoFunction function = RfcManager.getFunction("RFC_NAME");
        //获取参数列表
        JCoParameterList importParam = function.getImportParameterList();
        importParam.setValue("PARAM_1", paramMap.get("date"));
        //获取表字段
        JCoParameterList inTableParam = function.getTableParameterList();
        if (null!=paramMap.get("params") && !StrUtil.isEmpty(paramMap.get("params").toString()))
        {
            JCoTable tableInD = inTableParam.getTable("PARAMS");
            String[] werks = paramMap.get("params").toString().split(",");
            for (int i = 0; i < werks.length; i++)
            {
                tableInD.appendRow();
                tableInD.setValue("PARAMS",werks[i]);
            }
        }

        // 执行RFC
        RfcManager.execute(function);

        // 获取RFC返回的字段值
        JCoParameterList exportParam = function.getExportParameterList();
        // 遍历RFC返回的表对象
        JCoTable tb = function.getTableParameterList().getTable("RESULT_NAME");
        for (int i = 0; i < tb.getNumRows(); i++) {
            tb.setRow(i);
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("name1", tb.getString("NAME1"));
            list.add(map);
        }
        paramList.addAll(list);
        return list;
    }
}

