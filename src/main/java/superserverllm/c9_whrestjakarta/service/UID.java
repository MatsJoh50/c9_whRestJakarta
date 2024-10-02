package service;

import java.util.Random;

public class UID {
  private String uid = "";
  private Random random = new Random();

  public UID(int sections, int length) {
    generateUId(sections, length);
  }
  public String getUid() {
    return uid;
  }
  public void generateUId(int sections, int length){
    for (int i = 0; i < sections; i++) {
      genertateShortUid(length);
      if(i < sections - 1){
      this.uid = this.uid.concat("-");
      }
    }
  }

  public void genertateShortUid(int value) {
    for (int j = 0; j < value; j++) {
      if(randomBoolean()){
        if(randomBoolean()){
        this.uid = this.uid.concat(Character.toString(randomAlpha()));
        }else {
        this.uid = this.uid.concat(Character.toString(randomCapitalAlpha()));
        }
      } else {
        this.uid = this.uid.concat(Character.toString(randomDigital()));
      }
    }
  }

  private char randomAlpha(){
    char randomChar = (char)(randomInt(26) + 65);
    return randomChar;
  };
  private char randomCapitalAlpha(){
    char randomChar = (char)(randomInt(26) + 97);
    return randomChar;
  };
  private char randomDigital(){
    char randomChar = (char)(randomInt(10) + 48);
    return randomChar;
  };

  private int randomInt(){
    int randomInt = random.nextInt(9);
    return randomInt;
  };
  private int randomInt(int range){
    int randomInt = random.nextInt(range);
    return randomInt;
  };
  private boolean randomBoolean(){
    return random.nextBoolean();
  }
}
