package khuong.com.midterm_java.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    public Integer id;
    public String name;
    public List<Long> userIds;
}
