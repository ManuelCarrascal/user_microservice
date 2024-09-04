package emazon.user.domain.spi;

public interface IRolePersistencePort {
    Long getRoleId(String roleName);
}
