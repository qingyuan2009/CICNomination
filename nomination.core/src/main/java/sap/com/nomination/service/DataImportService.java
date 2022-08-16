package sap.com.nomination.service;

import sap.com.nomination.pojo.Bundle;
import sap.com.nomination.view.VoteView;

import java.util.Optional;
import java.util.UUID;

public interface DataImportService {

    void dataImport(Bundle bundle);
}
