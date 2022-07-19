package r.ian.ianlabtest.sec.role;

/**
 * User role for security considerations
 *
 *
 * @author Melton Smith
 * @since 29.07.2021
 */
public enum UserRole {
    REGISTERED("Registered", "Registered but unchecked"),
    SENT("Sent", "Sent for approval"),
    APPROVED("Approved", "Approved profile"),
    REJECTED("Rejected", "Rejected profile");

    private final String name;
    private final String title;

    UserRole(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getTitle(){
        return title;
    }

    public String getSomething(){
        return name;
    }
}
