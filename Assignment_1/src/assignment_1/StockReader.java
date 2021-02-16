package assignment_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.Writer;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
//import java.io.Reader;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.text.WordUtils;

//import org.apache.commons.lang.WordUtils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

//import assignment_1.Stocks;
//import assignment_1.StockList;

public class StockReader {
	static StockList stocks = null;
	public static boolean isValidFormat(String value) {
		LocalDateTime ldt = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			ldt = LocalDateTime.parse(value, formatter);
			String result = ldt.format(formatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, formatter);
				String result = ld.format(formatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, formatter);
					String result = lt.format(formatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					// Debugging purposes
					// e2.printStackTrace();
				}
			}
		}

		return false;
	}

	private static String parseInput(String file) {
		// try using stringbuilder? string obj that can be modified. like an array
		// source office hours
		// definitely have to use a try catch block
		// source office hours
		StringBuilder stringBuilder = new StringBuilder();
		try {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);

			String currString;
			while ((currString = bReader.readLine()) != null) {
				// string and then a new line
				stringBuilder.append(currString).append("\n");
			}
			fReader.close();
		}
		// file not found exception, ioexception
		// ask how to make it a loop once exception is thrown
		catch (FileNotFoundException f) {
			System.out.println("The file " + file + " could not be found.");
			return "wrong";
		} catch (IOException i) {
			System.out.println("The file " + file + " is not formatted correctly.");
			return "wrong";
		} catch (JsonParseException jpe) {
			System.out.println("The file " + file + "is not formatted correctly.");
			return "wrong";
		}
		// convert stringbuilder back to string
		return stringBuilder.toString();
	}

	// option 1
	// @SuppressWarnings("deprecation")
	private static void printAll(StockList x) {
		for (Stocks yeah : x.data) {
			String printString = yeah.getName() + "," + " symbol " + yeah.getTicker() + ", started on " + yeah.getDate()
					+ ", listed on " + yeah.getExchangeCode() + ", " + yeah.getDescription();
			System.out.println(WordUtils.wrap(printString, 65, "\n\t", true));
		}
	}

	// main method
	public static void main(String[] args) throws FileNotFoundException {
		//StockList stocks = null;
		Scanner in = new Scanner(System.in);
		String fileString = "";
		Gson gs = new GsonBuilder().setPrettyPrinting().create();
		StockList stocks = new StockList();
		//boolean file_io_done = false;

		String jsonString = "";
		
		while (true) {
			System.out.println("What is the name of the company file?");
			try {
				fileString = in.next();
				jsonString = parseInput(fileString);
				stocks = gs.fromJson(jsonString, StockList.class);
				break;
			}
			catch (Exception e) {
				System.out.print("File Reading Failed. Try Again.\n\n");
				continue;
			}
			
			//file_io_done = true;
		}
			
			
		boolean done = false;
		while (done == false) {
				// System.out.println(stocks.getByIndex(0).getName());
				// printAll(stocks);
				// System.out.println(jsonString);
				// Stocks stocks = new Gson().fromJson(jsonString, Stocks.class);
				System.out.println("\n" + "\t 1) Display all public companies");
				System.out.println("\t 2) Search for a stock (by ticker)");
				System.out.println("\t 3) Search for all stocks on an exchange");
				System.out.println("\t 4) Add a new company/stocks");
				System.out.println("\t 5) Remove a company");
				System.out.println("\t 6) Sort companies");
				System.out.println("\t 7) Exit");

				System.out.println("What would you like to do?");
				int option = in.nextInt();

				// use a try catch block to catch things that aren't numbers
				try {
					while (option < 1 || option > 7) {
						System.out.println("\n" + "That is not a valid option.");
						// asks for another number
						System.out.println("\n" + "What would you like to do?");
						option = in.nextInt();
						in.nextLine();
					}
					if (option == 1) {
						// ask about date
						// ask about spacing characters in new lines
						printAll(stocks);
					} else if (option == 2) {
						//boolean added 
						boolean tick = true;
						in.nextLine();
						while (tick) {
							System.out.println("\n" + "What is the name of the company you would like to search for?");
							// in.nextLine();
							String tickerString = in.nextLine();
							// System.out.println(tickerString);

							for (int i = 0; i < stocks.getData().size(); i++) {
								String stocktickerString = stocks.getByIndex(i).getTicker();
								// System.out.println("ticker: " + stocktickerString);

								if (tickerString.equalsIgnoreCase(stocktickerString)) {
									System.out.println("\n" + stocks.getByIndex(i).getName() + ", symbol "
											+ stocks.getByIndex(i).getTicker() + ", started on "
											+ stocks.getByIndex(i).getDate() + ", listed on "
											+ stocks.getByIndex(i).getExchangeCode());
									tick = false;
								}
							}
							if (tick == true) {
								System.out.println("\n" + tickerString + " could not be found.");
							}
						}
					} else if (option == 3) {
						boolean xchange = true;
						while (xchange) {
							System.out.println("\n" + "What stock exchange would you like to search for?");
							in.nextLine();
							String xchangeString = in.nextLine();
							for (int i = 0; i < stocks.getData().size(); i++) {
								String exString = stocks.getByIndex(i).getExchangeCode();
								if (xchangeString.equalsIgnoreCase(exString)) {
									ArrayList<String> ar = new ArrayList<String>();
									ar.add(stocks.getByIndex(i).getTicker());
									for (int j = 0; j < ar.size() - 1; j++) {
										System.out.print(ar.get(j) + " ");
									}
									System.out.print(ar.get(ar.size()-1) + " and ");
								}
							}
							System.out.print("found on " + xchangeString);
							xchange = false;
							if (xchange == true) {
								System.out.println("\n" + "No exchange named " + xchangeString + " found.");
							}
						}
					} else if (option == 4) {
						boolean added = false;
						while (!added) {
							System.out.println("\n" + "What is the name of the company you would like to add?");
							in.nextLine();
							String inputString = in.nextLine();
							// StockList xList = new StockList();
							// current modification exception: ask
							for (Stocks x : stocks.data) {
								if (inputString.equalsIgnoreCase(x.getTicker())) {
									System.out.println("\n" + "There is already an entry for " + inputString + ".");
									System.out.println("\n" + "What is the name of the company you would like to add?");
									inputString = in.nextLine();
								}
							}
							System.out.println("\n" + "What is the stock symbol of " + inputString + "?");
							String symbolString = in.nextLine();
							System.out.println("\n" + "What is the start date of " + inputString + "?");
							String start = in.nextLine();
							while (!isValidFormat(start)) {
								System.out.println("Not a valid date. Use the format YYYY-MM-DD.");
								System.out.println("\n" + "What is the start date of " + inputString + "?");
								start = in.nextLine();
							}
							System.out.println("\n" + "What is the exchange where " + inputString + " is listed?");
							String exchangeString = in.nextLine();
							System.out.println("\n" + "What is the description for " + inputString + "?");
							String descriptionString = in.nextLine();
							// System.out.println(inputString);

							// create new object
							Stocks newStocks = new Stocks();
							newStocks.setName(inputString);
							newStocks.setDate(start);
							newStocks.setDescription(descriptionString);
							newStocks.setExchangeCode(exchangeString);
							newStocks.setTicker(symbolString);
							stocks.addStock(newStocks);
							
				
//							for (Stocks s : stocks.data) {
//								System.out.println(s.toString());
//							}
							// stocks.setData(stocks.data);
							// display message
							//System.out.println(newStocks.toString());
							System.out.println("\n" + "There is now a new entry for: \n");
							System.out.println("\n" + newStocks.getName() + ", started on " + newStocks.getDate()
									+ ", listed on " + newStocks.getExchangeCode() + ", " + newStocks.getDescription());
							// will break out of the loop
							added = true;

						}
					} else if (option == 5) {
						// remove
						for (int i = 0; i < stocks.getData().size(); i++) {
							int count = i + 1;
							System.out.println("\n" + "\t" + count + ") " + stocks.getByIndex(i).getName());
						}
						System.out.println("\n" + "Which company would you like to remove?");
						in.nextLine();
						int removeInt = in.nextInt();

						if (removeInt < stocks.data.size()) {
							String companyString = stocks.getByIndex(removeInt - 1).getName();
							System.out.println(stocks.getData().remove(removeInt - 1));
							// System.out.println(stocks.getData());
							System.out.println("\n" + companyString + " is now removed.");
						}

						else {
							System.out.println("\n" + "This company does not exist." + "\n");
						}
					} else if (option == 6) {
						// sort
						System.out.println("\t 1) A to Z" + "\n \t 2) Z to A");
						System.out.println("\n" + "How would you like to sort by?");
						int choice = in.nextInt();
						if (choice == 1) {
							Stocks s = new Stocks();
							stocks.data.sort(s);
//							for(Stocks st: stocks.data) {
//								System.out.println(st.getName());
//							}
							System.out
									.println("\n" + "Your companies are now sorted from in alphabetical order (A-Z).");
						} else if (choice == 2) {
							stocks.reverseStock(stocks);
//							for(Stocks st: stocks.data) {
//								System.out.println(st.getName());
//							}
							System.out
									.println("\n" + "Your companies are now sorted from in alphabetical order (Z-A).");
						}
					} else if (option == 7) {
						System.out.println("\t 1) Yes");
						System.out.println("\t 2) No");
						System.out.println("\n" + "Would you like to save your edits?");
						int save = in.nextInt();
						while (save < 1 || save > 2) {
							System.out.println("\n" + "This is not a valid option.");
							System.out.println("\n" + "Would you like to save your edits?");
							save = in.nextInt();
							in.nextLine();
						}
						if (save == 1) {
							// save
							String json = gs.toJson(stocks);
							FileWriter fr;
							try {
//								System.out.println(stocks.getData().size());
//								for (Stocks s : stocks.data) {
//									System.out.println(s.toString());
//								}
								fr = new FileWriter(fileString);
								fr.write(json);
								fr.close();
//									
							} catch (IOException ioe) {
								ioe.getMessage();
							} finally {
								System.out.println("\n" + "Your edits have been saved to stock.json");
							}
							// System.out.println("\n" + "Your edits have been saved to stock.json");

							System.out.println("\n" + "Thank you for using my program!");
							System.exit(0);
						} else {
							System.out.println("\n" + "Thank you for using my program!");
							System.exit(0);
						}
					}
					// input mismatch will catch an input that does not match or is out of range
				} catch (InputMismatchException e) {
					// TODO: handle exception
					System.out.println("\n" + "That is not a valid option.");
				}

			}

			in.close();
		}

}
