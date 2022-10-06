package service;

import model.Installment;
import model.Summary;

import java.util.List;

public interface SummaryService {
    Summary calculate(List<Installment> installmentList);
}
