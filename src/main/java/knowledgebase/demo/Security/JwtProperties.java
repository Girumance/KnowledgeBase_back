package knowledgebase.demo.Security;

public class JwtProperties {

    public static final String SECRET="FINALPROJECT";
    public static final int EXPIRE=864000000; //Almost for 10 days
    public static final String TOKE_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";
}
