package vboot.common.mvc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData {

    private Integer total;

    private List<Map<String, Object>> items;

}
