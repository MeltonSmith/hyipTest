package r.ian.ianlabtest.sec.role;

/**
 * User role for security considerations
 *
 *
 * @author Melton Smith
 * @since 29.07.2021
 */
public enum UserRole {
    REGISTERED("Registered"),
    SENT("Sent"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
