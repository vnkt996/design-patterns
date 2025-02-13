package behavioral.chain_of_responsibility;

class Request {
    private String user;
    private String role;
    private String data;

    public Request(String user, String role, String data) {
        this.user = user;
        this.role = role;
        this.data = data;
    }

    public String getUser() { return user; }
    public String getRole() { return role; }
    public String getData() { return data; }
}

abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public void check(Request request) {
        if (handle(request) && next != null) {
            next.check(request);
        }
    }

    protected abstract boolean handle(Request request);
}

class AuthenticationMiddleware extends Middleware {
    @Override
    protected boolean handle(Request request) {
        if (request.getUser() == null) {
            System.out.println("Authentication failed: User not logged in.");
            return false;
        }
        System.out.println("Authentication successful for user: " + request.getUser());
        return true;
    }
}

class AuthorizationMiddleware extends Middleware {
    @Override
    protected boolean handle(Request request) {
        if (!"admin".equals(request.getRole())) {
            System.out.println("Authorization failed: User does not have admin rights.");
            return false;
        }
        System.out.println("Authorization successful: User has admin rights.");
        return true;
    }
}

class ValidationMiddleware extends Middleware {
    @Override
    protected boolean handle(Request request) {
        if (request.getData() == null || request.getData().isEmpty()) {
            System.out.println("Validation failed: Request data is missing.");
            return false;
        }
        System.out.println("Validation successful: Request data is valid.");
        return true;
    }
}

class MiddlewareChain {
    public Middleware getMiddleWare() {
        // Create middleware chain: Authentication → Authorization → Validation
        Middleware chain = new AuthenticationMiddleware();
        chain.linkWith(new AuthorizationMiddleware())
             .linkWith(new ValidationMiddleware());
        return chain;
    }
}

public class cor {
    public static void main(String[] args) {
        Middleware chain = new MiddlewareChain().getMiddleWare();

        // Sample request (Valid Case)
        System.out.println("\n--- Valid Request ---");
        Request validRequest = new Request("JohnDoe", "admin", "Some important data");
        chain.check(validRequest);

        // Sample request (Authentication Failure)
        System.out.println("\n--- Authentication Failed ---");
        Request noAuthRequest = new Request(null, "admin", "Some data");
        chain.check(noAuthRequest);

        // Sample request (Authorization Failure)
        System.out.println("\n--- Authorization Failed ---");
        Request noAuthzRequest = new Request("JohnDoe", "user", "Some data");
        chain.check(noAuthzRequest);

        // Sample request (Validation Failure)
        System.out.println("\n--- Validation Failed ---");
        Request invalidRequest = new Request("JohnDoe", "admin", "");
        chain.check(invalidRequest);
    }
}
