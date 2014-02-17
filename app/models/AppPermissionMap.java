package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ruijiang
 * Date: 2/15/14
 * Time: 11:03 PM
 */

@Entity
@Table(name = "app_permission_map")
public class AppPermissionMap implements Serializable{

    private static final long serialVersionUID = 4264546498700495061L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission", nullable = false)
    private Permission permission;

    @Id
    @Column(name = "app")
    private Long app;

    @Id
    @Column(name = "permission")
    private Long permissionId;

    public void setApp(Long app) {
        this.app = app;
    }

    public void setPermission(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Long getApp() {
        return app;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public Permission getPermission() {
        return this.permission;
    }


}

