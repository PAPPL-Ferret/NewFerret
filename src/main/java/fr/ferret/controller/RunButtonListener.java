package fr.ferret.controller;

import fr.ferret.FerretTest;
import fr.ferret.view.FerretFrame;
import fr.ferret.view.panel.RegionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Listens events of the run button and sends input data to the model
 */
public class RunButtonListener implements ActionListener {
    /**
     * The ferret frame
     */
    private final FerretFrame frame;

    /**
     * @param frame The ferret frame
     * @param runButton The button to listen
     */
    public RunButtonListener(FerretFrame frame, JButton runButton) {
        this.frame = frame;
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser saveFileChooser = new JFileChooser();
        String fileNameAndPath;
        saveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        saveFileChooser.setDialogTitle(FerretTest.locale.getString("run.save"));
        int returnVal = saveFileChooser.showSaveDialog(frame.getRunPanel());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = saveFileChooser.getSelectedFile();
            fileNameAndPath = file.getAbsolutePath();
            //fileLocation.setText("File Location: " + fileNameAndPath);
            validateInfosAndRun(fileNameAndPath);
        }
    }

    private void validateInfosAndRun(String fileNameAndPath) {
        //Reset borders
        getFrame().getRegionPanel().setBorder(null);
        getFrame().getLocusPanel().getChromosomeList().setBorder(null);
        getFrame().getLocusPanel().getInputStart().setBorder(null);
        getFrame().getLocusPanel().getInputEnd().setBorder(null);

        //Selected populations for the model
        ArrayList<CharSequence> populations = new ArrayList<>();
        RegionPanel panel = frame.getRegionPanel();
        for (RegionPanel.SubPanel regionPanel : panel.getRegions()) {
            for (int i = 0; i < regionPanel.getCheckBoxes().length; i++) {
                if (regionPanel.getCheckBoxes()[i].isSelected()) {
                    //Add the selected region to the populations list
                    populations.add(regionPanel.getRegion().getZones()[i]);
                }
            }
        }

        boolean populationSelected = !populations.isEmpty();

        // Chr position input method
        String chrSelected = (String) frame.getLocusPanel().getChromosomeList().getSelectedItem();
        boolean isChrSelected = !chrSelected.equals(" ");

        //TODO DEPENDS ON THE SELECTED PANEL
        String startPosition = frame.getLocusPanel().getInputStart().getText();
        String endPosition = frame.getLocusPanel().getInputEnd().getText();

        boolean startSelected = !startPosition.isEmpty();
        boolean endSelected = !endPosition.isEmpty();
        boolean startEndValid = true, withinRange = true;
        int chrEndBound = 0;

        if (startSelected && endSelected) {
            int tempEndPos = -1, tempStartPos = -1;
            try {
                tempStartPos = Integer.parseInt(startPosition);
            } catch (NumberFormatException ex) {
                startSelected = false;
            }
            try {
                tempEndPos = Integer.parseInt(endPosition);
            } catch (NumberFormatException ex) {
                endSelected = false;
            }
            if (startSelected && endSelected) {
                startEndValid = (tempEndPos >= tempStartPos);
                if (startEndValid) {
                    Map<String, Integer> chrMap = new HashMap<>();
                    if (FerretTest.config.getSelectedHumanGenome() == HumanGenomeVersions.V19) {
                        //Avoid too much if/else
                        chrMap.put("X", 155270560);
                        chrMap.put("1", 249250621);
                        chrMap.put("2", 243199373);
                        chrMap.put("3", 198022430);
                        chrMap.put("4", 191154276);
                        chrMap.put("5", 180915260);
                        chrMap.put("6", 171115067);
                        chrMap.put("7", 159138663);
                        chrMap.put("8", 146364022);
                        chrMap.put("9", 141213431);
                        chrMap.put("10", 135534747);
                        chrMap.put("11", 135006516);
                        chrMap.put("12", 133851895);
                        chrMap.put("13", 115169878);
                        chrMap.put("14", 107349540);
                        chrMap.put("15", 102531392);
                        chrMap.put("16", 90354753);
                        chrMap.put("17", 81195210);
                        chrMap.put("18", 78077248);
                        chrMap.put("19", 59128983);
                        chrMap.put("20", 63025520);
                        chrMap.put("21", 48129895);
                        chrMap.put("22", 51304566);

                        int validEnd = chrMap.get(chrSelected);
                        if (tempEndPos > validEnd || tempStartPos < 1) {
                            withinRange = false;
                            chrEndBound = validEnd;
                        }
                    } else {
                        chrMap.put("X", 156040895);
                        chrMap.put("1", 248956422);
                        chrMap.put("2", 242193529);
                        chrMap.put("3", 198295559);
                        chrMap.put("4", 190214555);
                        chrMap.put("5", 181538259);
                        chrMap.put("6", 170805979);
                        chrMap.put("7", 159345973);
                        chrMap.put("8", 145138636);
                        chrMap.put("9", 138394717);
                        chrMap.put("10", 133797422);
                        chrMap.put("11", 135086622);
                        chrMap.put("12", 133275309);
                        chrMap.put("13", 114364328);
                        chrMap.put("14", 107043718);
                        chrMap.put("15", 101991189);
                        chrMap.put("16", 90338345);
                        chrMap.put("17", 83257441);
                        chrMap.put("18", 80373285);
                        chrMap.put("19", 58617616);
                        chrMap.put("20", 64444167);
                        chrMap.put("21", 46709983);
                        chrMap.put("22", 50818468);

                        int validEnd = chrMap.get(chrSelected);
                        if (tempEndPos > validEnd || tempStartPos < 1) {
                            withinRange = false;
                            chrEndBound = validEnd;
                        }
                    }
                }
            }
        }

        //Valid input
        if (isChrSelected && populationSelected && startSelected && endSelected && startEndValid && withinRange) {
            FerretTest.log.log(Level.INFO, "Starting gene research...");
            //TODO ça, doit être dans le modèle

            /*inputRegion[] queries = {new inputRegion(chrSelected, Integer.parseInt(startPosition), Integer.parseInt(endPosition))};

            // if not get esp, string is none, else if get only ref, then string is ref, else string is both
            // this should be combined with the one single call to Ferret later
            // HERE
            final Integer[] variants = {0};
            String output = null;

            switch (currFileOut[0]){
                case ALL:
                    output = "all";
                    break;
                case FRQ:
                    output = "freq";
                    break;
                case VCF:
                    output = "vcf";
                    break;
            }

            String webAddress = null;
            if (currVersion[0] == version1KG.ONE){
                webAddress = "http://ftp.1000genomes.ebi.ac.uk/vol1/ftp/release/20110521/ALL.chr$.phase1_release_v3.20101123.snps_indels_svs.genotypes.vcf.gz";
            } else if (currVersion[0] == version1KG.THREE & defaultHG[0]){
                webAddress = "http://ftp.1000genomes.ebi.ac.uk/vol1/ftp/release/20130502/ALL.chr$.phase3_shapeit2_mvncall_integrated_v5a.20130502.genotypes.vcf.gz";
            } else if (currVersion[0] == version1KG.THREE & !defaultHG[0]){
                webAddress = "http://ftp.1000genomes.ebi.ac.uk/vol1/ftp/release/20130502/supporting/GRCh38_positions/ALL.chr$.phase3_shapeit2_mvncall_integrated_v3plus_nounphased.rsID.genotypes.GRCh38_dbSNP_no_SVs.vcf.gz";
            }

            //FIXME This is the new ferret URL from Sophie
            //webAddress = "http://ftp.1000genomes.ebi.ac.uk/vol1/ftp/release/20130502/?fbclid=IwAR0kMq3tB0cjZ5L49gfR4_uXSUgM6RK5VTeaM9O_EVXxQ0856Cnc7kmIBL8";
            webAddress = "http://ftp.1000genomes.ebi.ac.uk/vol1/ftp/release/20130502/ALL.chr$.phase3_shapeit2_mvncall_integrated_v5b.20130502.genotypes.vcf.gz";

            final FerretData currFerretWorker = new FerretData(queries, populations, fileNameAndPath, getESP, progressText, webAddress, mafThreshold[0], ESPMAFBoolean[0] , output);

            currFerretWorker.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    switch (evt.getPropertyName()){
                        case "progress":
                            progressBar.setValue((Integer) evt.getNewValue());
                        case "state":
                            try{
                                switch ((StateValue) evt.getNewValue()){
                                    case DONE:
                                        progressWindow.setVisible(false);
                                        try{
                                            variants[0] = currFerretWorker.get();
                                        } catch (ExecutionException e){
                                            e.printStackTrace();
                                            variants[0] = -1;
                                        } catch (InterruptedException e){
                                            e.printStackTrace();
                                            variants[0] = -1;
                                        }

                                        new File("evsClient0_15.jar").delete();
                                        Object[] options ={"Yes","No"};
                                        int choice;
                                        System.out.println("Total Time: " + (System.nanoTime() - startTime));
                                        if(variants[0] == 1){
                                            choice = JOptionPane.showOptionDialog(SNPFerret,
                                                    "Files have been downloaded\nDo you want to close Ferret?",
                                                    "Close Ferret?",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    options,
                                                    null);
                                        } else if(variants[0] == -3){
                                            choice = JOptionPane.showOptionDialog(SNPFerret,
                                                    "After applying the MAF threshold, no variants were found"
                                                            + "\nDo you want to close Ferret?",
                                                    "Close Ferret?",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    options,
                                                    null);
                                        } else if(variants[0] == 0) {
                                            choice = JOptionPane.showOptionDialog(SNPFerret,
                                                    "No variants were found in this region\nDo you want to close Ferret?",
                                                    "Close Ferret?",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    options,
                                                    null);
                                        } else {
                                            choice = JOptionPane.showOptionDialog(SNPFerret,
                                                    "Ferret has encountered a problem downloading data. \n"
                                                            + "Please try again later or consult the FAQ. \nDo you want to close Ferret?",
                                                    "Close Ferret?",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null,
                                                    options,
                                                    null);
                                        }
                                        if(choice == JOptionPane.YES_OPTION ){
                                            SNPFerret.dispose();
                                            System.exit(0);
                                        }else{
                                            enableComponents(SNPFerret, true);
                                            if (currFileOut[0] == fileOutput.VCF){
                                                snpESPCheckBox.setEnabled(false);
                                                geneESPCheckBox.setEnabled(false);
                                                chrESPCheckBox.setEnabled(false);
                                            }
                                            for(int i = 0; i < asnsub.length; i++) {
                                                asnPanel.add(asnsub[i]);
                                                if(asnsub[i].getText().contains("n=0")){
                                                    asnsub[i].setEnabled(false);
                                                }
                                            }
                                            progressText.setText("Initializing...");
                                            progressBar.setValue(0);
                                            checkBoxReset();
                                        }
                                        break;
                                    case STARTED:
                                    case PENDING:
                                        Dimension windowSize = SNPFerret.getSize();
                                        progressWindow.setSize(new Dimension((int)(windowSize.width*.5),(int)(windowSize.height*.2)));
                                        progressWindow.setLocationRelativeTo(SNPFerret);
                                        progressWindow.setVisible(true);
                                        enableComponents(SNPFerret, false);
                                }
                            }catch(ClassCastException e){}
                    }

                }
            });
            currFerretWorker.execute();*/
        } else { //Invalid input
            StringBuffer errorMessage = new StringBuffer(FerretTest.locale.getString("run.fixerrors"));
            if (!isChrSelected) {
                errorMessage.append("\n " + FerretTest.locale.getString("run.selectchr"));
                frame.getLocusPanel().getChromosomeList().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
            if (!populationSelected) {
                errorMessage.append("\n " + FerretTest.locale.getString("run.selectpop"));
                frame.getRegionPanel().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
            if (!startSelected) {
                errorMessage.append("\n " + FerretTest.locale.getString("run.startpos"));
                frame.getLocusPanel().getInputStart().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
            if (!endSelected) {
                errorMessage.append("\n " + FerretTest.locale.getString("run.endpos"));
                frame.getLocusPanel().getInputEnd().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
            if (!startEndValid) {
                errorMessage.append("\n " + FerretTest.locale.getString("run.invalidstart"));
                frame.getLocusPanel().getInputStart().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                frame.getLocusPanel().getInputEnd().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
            if (!withinRange) {
                errorMessage.append("\n " + FerretTest.locale.getString("run.invalidpos.1") + " " + chrSelected + " " + FerretTest.locale.getString("run.invalidpos.2") + " " + chrEndBound);
                frame.getLocusPanel().getInputStart().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                frame.getLocusPanel().getInputEnd().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
            }
            JOptionPane.showMessageDialog(frame, errorMessage, FerretTest.locale.getString("run.error"), JOptionPane.OK_OPTION);
        }
    }

    public FerretFrame getFrame() {
        return frame;
    }
}
