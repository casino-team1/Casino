/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Erik Hess
 */
public class Rules {

    private int subResult;
    private int total;
    private int upperTotal;
    private int lowerTotal;
    private int upperCounter = 0;
    private int bonusUpperTotal;
    private int lowerCounter = 0;
    private int betNum = 0;
    private int win;
    private int winAmount;
    private int newAmount;
    private int balance;
    private boolean upperBool = false;
    private boolean lowerBool = false;

    public int createSubResult(String label, ArrayList<Dice> dicearray) {
        subResult = 0;
        HashMap<Integer, Integer> dicemap = new HashMap<>();
        for (int i = 1; i < 7; i++) {
            dicemap.put(i, 0);
        }
        for (Dice d : dicearray) {
            if (dicemap.containsKey(d.getValue())) {
                dicemap.put(d.getValue(), dicemap.get(d.getValue()) + 1);
            } else {
                dicemap.put(d.getValue(), 1);
            }
        }

        switch (label) {

            case "EINER":
                subResult = dicemap.get(1);
                upperTotal += subResult;
                upperCounter++;
                break;
            case "ZWEIER":
                subResult = 2 * dicemap.get(2);
                upperTotal += subResult;
                upperCounter++;
                break;
            case "DREIER":
                subResult = 3 * dicemap.get(3);
                upperTotal += subResult;
                upperCounter++;
                break;
            case "VIERER":
                subResult = 4 * dicemap.get(4);
                upperTotal += subResult;
                upperCounter++;
                break;
            case "FÜNFER":
                subResult = 5 * dicemap.get(5);
                upperTotal += subResult;
                upperCounter++;
                break;
            case "SECHSER":
                subResult = 6 * dicemap.get(6);
                upperTotal += subResult;
                upperCounter++;
                break;
            case "DREI GLEICHE":
                for (int i = 1; i < 7; i++) {
                    if (dicemap.get(i) == 3 || dicemap.get(i) == 4 || dicemap.get(i) == 5) {
                        subResult = i * 3;
                    }
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
            case "VIER GLEICHE":
                for (int i = 1; i < 7; i++) {
                    if (dicemap.get(i) == 4 || dicemap.get(i) == 5) {
                        subResult = i * 4;
                    }
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
            case "FULL HOUSE":
                int re;
                int sult;
                for (int i = 1; i < 7; i++) {
                    if (dicemap.get(i) == 3) {
                        re = i * 3;
                        for (int j = 1; j < 7; j++) {
                            if (dicemap.get(j) == 2) {
                                sult = j * 2;
                                subResult = re + sult;
                            }
                        }
                    }
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
            case "KLEINE STRASSE":
                if (dicemap.get(1) == 1 && dicemap.get(2) == 1 && dicemap.get(3) == 1 && dicemap.get(4) == 1 && dicemap.get(5) == 1) {
                    subResult = 15;
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
            case "GROSSE STRASSE":
                if (dicemap.get(2) == 1 && dicemap.get(3) == 1 && dicemap.get(4) == 1 && dicemap.get(5) == 1 && dicemap.get(6) == 1) {
                    subResult = 20;
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
            case "YATZY":
                for (int i = 1; i < 7; i++) {
                    if (dicemap.get(i) == 5) {
                        subResult = 50;
                    }
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
            case "CHANCE":
                for (int i = 0; i < 5; i++) {
                    subResult += dicearray.get(i).getValue();
                }
                lowerTotal += subResult;
                lowerCounter++;
                break;
        }
        return subResult;
    }

    public void totalChecker() {
        if (upperCounter == 6) {
            upperBool = true;
            if (upperTotal >= 63) {
                bonusUpperTotal = upperTotal + 35;
            } else {
                bonusUpperTotal = upperTotal;
            }
        }
        if (lowerCounter == 7) {
            lowerBool = true;
        }

        if (upperBool == true && lowerBool == true) {
            total = lowerTotal + bonusUpperTotal;
        }
    }

    public void checkwin(int enemytotal) {
        if (total > enemytotal) {
            win = 1;
            winAmount = betNum + betNum;
            newAmount = balance + winAmount;
            //newamount = UserCentral.getInstance().getUser().getCurrentBalance() + winAmount;

            //UserCentral.getInstance().getUser().setCurrentBalance(newAmount);
        } else if (total < enemytotal) {
            win = 2;
            winAmount = 0;
            newAmount = balance + winAmount;
            //newamount = UserCentral.getInstance().getUser().getCurrentBalance() - winAmount;
            //UserCentral.getInstance().getUser().setCurrentBalance(newAmount);
        } else {
            win = 3;
            winAmount = betNum;
            newAmount = balance + winAmount;
            //newamount = UserCentral.getInstance().getUser().getCurrentBalance() + winAmount;
            //UserCentral.getInstance().getUser().setCurrentBalance(newAmount);
        }
    }

    public int getUppertotal() {
        return upperTotal;
    }

    public int getLowertotal() {
        return lowerTotal;
    }

    public int getBonusUppertotal() {
        return bonusUpperTotal;
    }

    public boolean isUpperbool() {
        return upperBool;
    }

    public boolean isLowerbool() {
        return lowerBool;
    }

    public int getTotal() {
        return total;
    }

    public void setUppercounter(int uppercounter) {
        this.upperCounter = uppercounter;
    }

    public void setLowercounter(int lowercounter) {
        this.lowerCounter = lowercounter;
    }

    public void setUpperbool(boolean upperbool) {
        this.upperBool = upperbool;
    }

    public void setLowerbool(boolean lowerbool) {
        this.lowerBool = lowerbool;
    }

    public void setUppertotal(int uppertotal) {
        this.upperTotal = uppertotal;
    }

    public void setLowertotal(int lowertotal) {
        this.lowerTotal = lowertotal;
    }

    public int getBetnum() {
        return betNum;
    }

    public void setBetnum(int betnum) {
        this.betNum = betnum;
    }

    public int getWin() {
        return win;
    }

    public int getWinAmount() {
        return winAmount;
    }

    public int getNewAmount() {
        return newAmount;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
