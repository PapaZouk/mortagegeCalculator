package service;

import model.InputData;

@SuppressWarnings("unused")
public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK: ";
    String RATE_NUMBER = "NR: ";
    String YEAR = "ROK: ";
    String MONTH = "MIESIĄC: ";
    String DATE = "DATA: ";
    String MONTHS = " MIESIĘCY ";
    String INSTALLMENT = "RATA: ";
    String CAPITAL = "KAPITAŁ: ";
    String INTEREST = "ODSETKI: ";
    String LEFT = "POZOSTAŁO: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " ZŁ ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputDataInfo(final InputData inputData);
}
