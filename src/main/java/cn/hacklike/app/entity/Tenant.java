package cn.hacklike.app.entity;

import cn.hacklike.common.LkEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Tenant extends LkEntity {

    private static final long serialVersionUID = 5719432974144905676L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String tenantName;

    private String tenantCode;

    private String tenantOauth;

    private String tenantPhone;

    private String tenantEmail;

    @Override
    public String toString() {
        return "Tenant{" +
                "id='" + id + '\'' +
                ", tenantName='" + tenantName + '\'' +
                ", tenantCode='" + tenantCode + '\'' +
                ", tenantOauth='" + tenantOauth + '\'' +
                ", tenantPhone='" + tenantPhone + '\'' +
                ", tenantEmail='" + tenantEmail + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    public String getTenantOauth() {
        return tenantOauth;
    }

    public void setTenantOauth(String tenantOauth) {
        this.tenantOauth = tenantOauth;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }
}
