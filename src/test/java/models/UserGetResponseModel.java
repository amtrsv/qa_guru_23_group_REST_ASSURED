package models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGetResponseModel {
     UserDataModel data;
     UserSupportModel support;
}
