package r.ian.ianlabtest.sec.role;

/**
 * User role for security considerations
 *
 *
 * @author Melton Smith
 * @since 29.07.2021
 */
public enum UserRole {
    REGISTERED("Registered", "Registered, waiting for approval"),
    SENT("Sent", "Sent for approval"),
    APPROVED("Approved", "Approved user"),
    REJECTED("Rejected", "Rejected user"),
    ADMIN("Admin", "Admin user"),
    ANONYMOUS("Anonymous", "Anonymous profile");

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
