//Mahika Bagri
//CSE 123 
//21 November 2025

import java.io.*;
import java.util.*;

// This class is a type of classzifier that can classify text and label it.
// It can be trained on text data, load in an algorithm, and save the algorithm to file.
// and it can obviously classify a given text block. 
public class Classifier {

    private ClassifierNode overallRoot;

    // Behavior: 
    //   - This method loads in a given algorithm tree using input from a scanner.
    // Parameters:
    //   - input: the scanner the user wants to load in as a classifiation algorithm.  
    //   - files should be formatted to have Line 1: Feature: <feature name>, Line 2:
    // Threshold: <threshold number> followed by either feature/thresholds under them and then 
    // their labels or the label in it's own line  
    // Exceptions:
    //   - if the input is null, an IllegalArgumentException is thrown
    //   - if the the algorithm is null after loading, an IllegalStateException is thrown
    public Classifier(Scanner input) {
        if(input == null){
            throw new IllegalArgumentException();
        }
        this.overallRoot = reader(input);
        if(this.overallRoot == null){
            throw new IllegalStateException();
        }
    }

    // Behavior: 
    //   - This method loads in a given algorithm current node of tree using input from a scanner.
    // Parameters:
    //   - input: the scanner the user wants to load in as a classifiation algorithm.  
    // Returns:
    //   - ClassifierNode: the current finished subtree   
    private ClassifierNode reader(Scanner input){
        if(!input.hasNextLine()){
            return null;
        }else{
            String line = input.nextLine();
            if(line.contains("Feature:")){
                int space = line.indexOf(" ");

                String feature = line.substring(space+1);

                line = input.nextLine();
                space = line.indexOf(" ");

                double threshold = Double.parseDouble(line.substring(space+1));

                return new ClassifierNode(feature, threshold, reader(input), reader(input));
            }else{
                return new ClassifierNode(line);
            }
        }

    }

    // Behavior: 
    //   - This method creates/trains given algorithm tree using input from a data.
    // Parameters:
    //   - data: the list of text data the user wants to train the algorithm on.  
    //   - labels: the list of labels of data the user wants to train the algorithm on.  
    // Exceptions:
    //   - if the data is null, an IllegalArgumentException is thrown
    //   - if the data is empty, an IllegalArgumentException is thrown
    //   - if the labels is null, an IllegalArgumentException is thrown
    //   - if the labels is empty, an IllegalArgumentException is thrown
    //   - if the number of labels isn't equal to number of text data
    //, an IllegalArgumentException is thrown
    public Classifier(List<TextBlock> data, List<String> labels) {
        if(data == null||labels == null||data.isEmpty()||labels.isEmpty()||
        data.size() != labels.size()){
            throw new IllegalArgumentException();
        }
        this.overallRoot = trainer(data, labels, this.overallRoot, 0);
    }

    // Behavior: 
    //   - This method trains given algorithm tree using input from a data.
    // Parameters:
    //   - data: the list of text data the user wants to train the algorithm on.  
    //   - labels: the list of labels of data the user wants to train the algorithm on.  
    //   - root: the node the algorithm is cureently training.  
    //   - index: the text number that the algorithm is currently training on.  
    // Returns:
    //   - ClassifierNode: the current finished subtree   
    private ClassifierNode trainer(List<TextBlock> data, List<String> labels, ClassifierNode root, int index){
        if(index == data.size()){
            return root;
        }
        
        String label = labels.get(index);
        TextBlock text = data.get(index);
        
        if(root == null){
            root = new ClassifierNode(label, text);
            return trainer(data, labels, root, index+1);
        }else{
            ClassifierNode labelNode = classify(text, root);
            String prediction = labelNode.label;

            if(prediction.equals(label)){
                return trainer(data, labels, root, index+1);
            }else{
                ClassifierNode parent = findParent(text, root);

                TextBlock textTwo = labelNode.text;
                String feature = text.findBiggestDifference(textTwo);
                double threshold = midpoint(text.get(feature), textTwo.get(feature));

                ClassifierNode left;
                ClassifierNode right;

                if(text.get(feature)<threshold){
                    left = new ClassifierNode(label, text);
                    right = new ClassifierNode(prediction, textTwo);
                }else{
                    left = new ClassifierNode(prediction, textTwo);
                    right = new ClassifierNode(label, text);
                }

                if(parent != null){
                    if(parent.left.label == prediction){
                        parent.left = new ClassifierNode(feature, threshold, left, right);
                    }else{
                        parent.right = new ClassifierNode(feature, threshold, left, right);
                    }
                }else{
                    root = new ClassifierNode(feature, threshold, left, right);
                }
                
                return trainer(data, labels, root, index+1);                
            }
        }
    }

    // Behavior: 
    //   - This method finds the parent of the leaf which classifies a text using our algorithm.
    // Parameters:
    //   - input: the textblock the user wants to classify. 
    //   - root: the current node that is being checked. 
    // Returns:
    //   - ClassifierNode: the decision point before where the text is labeled according 
    //to the algorithm 
    private ClassifierNode findParent(TextBlock input, ClassifierNode root) {
        if (root.label != null) {
            return null;
        }

        double wordFrequency = input.get(root.feature);

        if (wordFrequency < root.threshold) {
            if (root.left.label != null) {
                return root;
            } else {
                ClassifierNode leftParent = findParent(input, root.left);
                if (leftParent != null) {
                    return leftParent;
                } else {
                    return root;
                }
            }
        } else {
            if (root.right.label != null) {
                return root;
            } else {
                ClassifierNode rightParent = findParent(input, root.right);
                if (rightParent != null) {
                    return rightParent;
                } else {
                    return root;
                }
            }
        }
    }    

    // Behavior: 
    //   - This method classifies a text using our algorithm.
    // Parameters:
    //   - input: the textblock the user wants to classify. 
    // Returns:
    //   - String: the label of the text according to the algorithm
    // Exceptions:
    //   - if the input is null, an IllegalArgumentException is thrown
    public String classify(TextBlock input) {
        if(input == null){
            throw new IllegalArgumentException();
        }
        ClassifierNode labelNode = classify(input, overallRoot);
        return labelNode.label;
    }

    // Behavior: 
    //   - This method finds the leaf which classifies a text using our algorithm.
    // Parameters:
    //   - input: the textblock the user wants to classify. 
    //   - root: the current node that is being checked. 
    // Returns:
    //   - ClassifierNode: the node that labels the text according to the algorithm  
    private ClassifierNode classify(TextBlock input, ClassifierNode root){
        if(root.label == null){
            double wordFrequency = input.get(root.feature); 
            if(wordFrequency < root.threshold){
                return classify(input, root.left); 
            }else{
                return classify(input, root.right); 
            }           
        }else{
            return root;
        }
    }

    // Behavior: 
    //   - This method saves the algorithm to a given file as text using a printstream.
    //   - files is formatted to have Line 1: Feature: <feature name>, Line 2:
    // Threshold: <threshold number> followed by either feature/thresholds under them and then 
    // their labels or the label in it's own line  

    // Parameters:
    //   - output: the printstream the user wants to use. 
    // Exceptions:
    //   - if the output is null, an IllegalArgumentException is thrown
    public void save(PrintStream output){
        if(output == null){
            throw new IllegalArgumentException();
        }
        save(output, overallRoot);
    }

    // Behavior: 
    //   - This method saves the algorithm to a given file as text using a printstream.
    // Parameters:
    //   - output: the printstream the user wants to use. 
    //   - root: the current node thats being saved. 
    private void save(PrintStream output, ClassifierNode root){
        if(root != null){
            if(root.label == null){
                output.println("Feature: " + root.feature);
                output.println("Threshold: " + root.threshold);
                save(output, root.left);
                save(output, root.right);
            }else{
                output.println(root.label);
            }
        }
    }

    // this is a classifier node that is a node used to build a classifier tree
    // It contains a feature, threshols, left child, right child, label, and text
    private static class ClassifierNode {
        public final String feature; 
        public final double threshold; 

        public ClassifierNode left; 
        public ClassifierNode right; 

        public final String label;
        public final TextBlock text; 

        // Behavior: 
        //   - This method constructs a new ClassifierNode.
        // Parameters:
        //   - label: the label the user wants the node to have. 
        public ClassifierNode(String label){
            this(null, 0, null, null, label, null);
        }
        
        // Behavior: 
        //   - This method constructs a new ClassifierNode.
        // Parameters:
        //   - label: the label the user wants the node to have. 
        //   - text: the text that has that label. 
        public ClassifierNode(String label, TextBlock text){
            this(null, 0, null, null, label, text);
        }

        // Behavior: 
        //   - This method constructs a new ClassifierNode.
        // Parameters:
        //   - feature: the word that causes the node to split. 
        //   - threshold: the threshold for wether to go left or right in the split. 
        //   - left: the left child of the node. 
        //   - right: the right child of the node. 
        public ClassifierNode(String feature, double threshold, ClassifierNode left, 
        ClassifierNode right){
            this(feature, threshold, left, right, null, null);
        }

        // Behavior: 
        //   - This method constructs a new ClassifierNode.
        // Parameters:
        //   - label: the label the user wants the node to have. 
        //   - text: the text that has that label. 
        //   - feature: the word that causes the node to split. 
        //   - threshold: the threshold for wether to go left or right in the split. 
        //   - left: the left child of the node. 
        //   - right: the right child of the node. 
        public ClassifierNode(String feature, double threshold, 
        ClassifierNode left, ClassifierNode right, String label, TextBlock text){
            this.feature = feature;
            this.threshold = threshold;
            this.left = left;
            this.right = right;
            this.label = label;
            this.text = text;
        }
    }


    ////////////////////////////////////////////////////////////////////
    // PROVIDED METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!** //
    ////////////////////////////////////////////////////////////////////

    // Helper method to calculate the midpoint of two provided doubles.
    private static double midpoint(double one, double two) {
        return Math.min(one, two) + (Math.abs(one - two) / 2.0);
    }

    // Behavior: Calculates the accuracy of this model on provided Lists of 
    //           testing 'data' and corresponding 'labels'. The label for a 
    //           datapoint at an index within 'data' should be found at the 
    //           same index within 'labels'.
    // Exceptions: IllegalArgumentException if the number of datapoints doesn't match the number 
    //             of provided labels
    // Returns: a map storing the classification accuracy for each of the encountered labels when
    //          classifying
    // Parameters: data - the list of TextBlock objects to classify. Should be non-null.
    //             labels - the list of expected labels for each TextBlock object. 
    //             Should be non-null.
    public Map<String, Double> calculateAccuracy(List<TextBlock> data, List<String> labels) {
        // Check to make sure the lists have the same size (each datapoint has an expected label)
        if (data.size() != labels.size()) {
            throw new IllegalArgumentException(
                    String.format("Length of provided data [%d] " +
                                    "doesn't match provided labels [%d]", 
                            data.size(), labels.size()));
        }

        // Create our total and correct maps for average calculation
        Map<String, Integer> labelToTotal = new HashMap<>();
        Map<String, Double> labelToCorrect = new HashMap<>();
        labelToTotal.put("Overall", 0);
        labelToCorrect.put("Overall", 0.0);

        for (int i = 0; i < data.size(); i++) {
            String result = classify(data.get(i));
            String label = labels.get(i);

            // Increment totals depending on resultant label
            labelToTotal.put(label, labelToTotal.getOrDefault(label, 0) + 1);
            labelToTotal.put("Overall", labelToTotal.get("Overall") + 1);
            if (result.equals(label)) {
                labelToCorrect.put(result, labelToCorrect.getOrDefault(result, 0.0) + 1);
                labelToCorrect.put("Overall", labelToCorrect.get("Overall") + 1);
            }
        }

        // Turn totals into accuracy percentage
        for (String label : labelToCorrect.keySet()) {
            labelToCorrect.put(label, labelToCorrect.get(label) / labelToTotal.get(label));
        }

        return labelToCorrect;
    }
}
