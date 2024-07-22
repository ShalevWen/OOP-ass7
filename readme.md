# Hypernym database generator

## Description

This project aims to generate a database of hypernym-hyponym relations given a corpus using predefined regex patterns.

## Requirements

To run this project, you will need the following:

- Java Development Kit (JDK) 8 or higher

Make sure to have these dependencies installed before proceeding with the installation steps.

## Installation

To install this project, follow these steps:

1. Clone the repo and compile the code using the following commands:
```shell
git clone https://github.com/ShalevWen/OOP-ass7.git
cd OOP-ass7
 javac -d out/ src/*.java
```

## Usage

First you will need to select a corpus, you have two options
1. Download and extract [this](https://drive.google.com/drive/folders/11aVnX9r-k5iy2GafZd-o5lBBgeNRuFDN) zip file containing a large prepared corpus.
3. Create a folder and place there any files you would like to analyze. make sure to wrap all noun phrases with `<np></np>`.

To create a database from the corpus, run the following command:
```shell
java -cp out CreateHypernymDatabase <corpus-folder-path> <output-path>
```

To get a hyponym list for a specific hypernym run the following command:
```shell
java -cp out DiscoverHypernym <corpus-folder-path> <hypernym> [db-path=./hypernym_db.txt]
```

`corpus-folder-path` will only be used if the file at db-path doesn't exist, in which case a database will be created at db-path and will be used to generate the list.


## Additional info

For additional information about the project, you can read the assignment details [here](https://github.com/ariecattan/biuoop2023/wiki/Assignment-7)