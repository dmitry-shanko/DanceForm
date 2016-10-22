package by.danceform.app.web.rest.vm;

import by.danceform.app.domain.user.User;
import by.danceform.app.dto.user.UserDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    private static final long serialVersionUID = 4113131634344268009L;

    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    private String createdBy;

    private ZonedDateTime createdDate;

    private String lastModifiedBy;

    private ZonedDateTime lastModifiedDate;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    public ManagedUserVM() {
    }

    public ManagedUserVM(User user) {
        super(user);
        this.id = user.getId();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.password = null;
    }

    public ManagedUserVM(Long id,
                         String login,
                         String password,
                         String firstName,
                         String lastName,
                         String email,
                         boolean activated,
                         String langKey,
                         Set<String> authorities,
                         String createdBy,
                         ZonedDateTime createdDate,
                         String lastModifiedBy,
                         ZonedDateTime lastModifiedDate) {
        super(id, login, firstName, lastName, email, activated, langKey, authorities);
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.password = password;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ManagedUserVM{" +
               "id=" +
               id +
               ", createdBy=" +
               createdBy +
               ", createdDate=" +
               createdDate +
               ", lastModifiedBy='" +
               lastModifiedBy +
               '\'' +
               ", lastModifiedDate=" +
               lastModifiedDate +
               "} " +
               super.toString();
    }
}
