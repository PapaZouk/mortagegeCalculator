package service;

import model.InputData;
import model.Installment;

import java.util.List;

@SuppressWarnings("unused")
public interface PrintingService {

    String INTEREST_SUM = "SUMA ODSETEK: ";
    String INSTALLMENT_NUMBER = "NR: ";
    String YEAR = " ROK: ";
    String MONTH = " MIESIĄC: ";
    String DATE = " DATA: ";
    String MONTHS = " MIESIĘCY ";
    String INSTALLMENT = " RATA: ";
    String CAPITAL = " KAPITAŁ: ";
    String INTEREST = "ODSETKI: ";
    String LEFT_AMOUNT = " POZOSTAŁA KWOTA: ";

    String LEFT_MONTHS = " POZOSTAŁO MIESIĘCY: ";
    String MORTGAGE_AMOUNT = "KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = "OKRES KREDYTOWANIA: ";

    String CURRENCY = " ZŁ ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    void printInputDataInfo(final InputData inputData);

    void printInstallments(List<Installment> installments);
}
