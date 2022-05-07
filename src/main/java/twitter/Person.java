package twitter;

public class Person { //to do, make abstract
    private String username; //acts as unique identifier
    private String name;

    public Person(){
    }

    public Person(String username, String name){
        this.username = username;
        this.name = name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
        return this.username;
    }

    public String getName(){
        return this.name;
    }


}
