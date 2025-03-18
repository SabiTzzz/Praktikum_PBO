package main;

import main.repository.*;
import main.service.*;
import main.interfaces.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TransaksiRepo transaksiRepo = new TransaksiRepo();

        TransaksiService transaksiService = new TransaksiService(transaksiRepo);

        MainMenu mainMenu = new MainMenu(reader, transaksiService);
        mainMenu.showLoginMenu();
    }
}
