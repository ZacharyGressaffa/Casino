package bicycle;

import java.util.Scanner;
public class Casino {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String Suit[] = {"Heart", "Diamond", "Club", "Spade"};
		String Class[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		Card[] Board = new Card [52];
		Card[] Deck = new Card [52];
		Card[] Build = new Card [10];
		Card[] Doublebuild = new Card [10];
		Card[] Triplebuild = new Card [10];
		Card[] Held = new Card [10];
		int count = 0;
		int round = 0;
		int tempindex = 0;
		int compensate1 = 4;
		int compensate2 = 8;
		int Play;
		int handchoice;
		int choice;
		int amount;
		int cardnum = 4;
		Card[] Pocket1 = new Card[52];
		Card[] Pocket2 = new Card[52];
		int Pocket1index = 0;
		int Pocket2index = 0;
		int P1score = 0;
		int P2score = 0;
		int P1spades = 0;
		int P2spades = 0;
		Card[] Hand1 = new Card[4];
		Card[] Hand2 = new Card[4];
		
		//Create deck
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				String temp = Suit[i];
				String temp2 = Class[j];
				Board[count] = new Card(temp, temp2, j + 1, j + 1);
				++count;
			}
		}
		//Give point cards values
			//Aces
			Board[0].PointValue(1);
			Board[13].PointValue(1);
			Board[26].PointValue(1);
			Board[39].PointValue(1);
			
			//Spades
			Board[51].PointValue(2);
			Board[50].PointValue(2);
			Board[49].PointValue(2);
			Board[48].PointValue(2);
			Board[47].PointValue(2);
			Board[46].PointValue(2);
			Board[45].PointValue(2);
			Board[44].PointValue(2);
			Board[43].PointValue(2);
			Board[42].PointValue(2);
			Board[41].PointValue(2);
			Board[40].PointValue(2);
			Board[39].PointValue(2);
			
			//2 of Spades
			Board[40].PointValue(1);
			
			//10 of Diamonds
			Board[22].PointValue(3);
			
		System.out.println("PAY ATTENTION TO CARD NUMBERS!!");
		//Shuffle deck
		for (int i = 0; i < 52; i++) {
			double p = Math.random() * 52;
			int index = (int)p;
			for (int j = 0; j < 52; j++) {
				if (Deck[index] != null) {
					index -= 1;
					if (index < 0) {
						index = 51;
					}
				}
		}
				Deck [index] = Board [i];
			}
		//Clear Board
		for(int i = 0; i < 52; i++) {
			Board[i] = null;
		}
		System.out.println("Board:");
		System.out.println("_____________________________");
		for(int i = 0; i < 4; i++) {
			Board[i] = Deck[i];
			System.out.println((i+1) + ") Value: " + Board[i].getValue() + "; " + Board[i]);
			
		}
		
		System.out.println("_____________________________");
		
		for(round = 0; round < 6; round++) {
			System.out.println("Cards are dealt, round: " + (round + 1));
			for (int hand = 0; hand < 4; hand++) {
				Hand1[hand] = Deck[cardnum]; 
				cardnum++;
			}
			for (int hand = 0; hand < 4; hand++) {
				Hand2[hand] = Deck[cardnum];
				cardnum++;
			}
			//Print hand
			for(int i = 0; i < 4; i++) {
				System.out.println("Player 1 Hand: ");
				System.out.println("-----------------------------");
				for (int hand = 0; hand < 4; hand++) {
					if(Hand1[hand] == null) {
						System.out.println((hand+1) + ") Used");
					}else {
						System.out.println((hand+1) + ") " + Hand1[hand]);
					}
				}
				System.out.println("-----------------------------");
				System.out.println("Player 1, " + "Turn " + (i+1) + ": ");
				System.out.println("Would you like to 1) Pick; 2) Diss; 3) Build; 4) Hold; 5); Build from board 6) Pick multiple from board");
				Play = scan.nextInt();
				while (Play == 5) {
					System.out.println("How many cards would you like to build?");
					amount = scan.nextInt();
					System.out.println("Pick card #1");
					int card1 = scan.nextInt();
					int cardTemp;
					for (int p = 0; p < amount - 1; p++) {
						System.out.println("Pick card #" + (p + 2));
						cardTemp = scan.nextInt();
					
						if(Build[card1 - 1] == null) {
							Board[card1 - 1].Build(Board[cardTemp - 1].getValue());
							Build[card1 - 1] = Board[cardTemp - 1];
						}else {
							if(Doublebuild[card1 - 1] == null) {
							Board[card1 - 1].Build(Board[cardTemp - 1].getValue());
							Doublebuild[card1 - 1] = Board[cardTemp - 1];
							Build[cardTemp - 1] = null;
							}else {
								Triplebuild[card1 - 1] = Board[cardTemp - 1];
							}
							
						}
						if(Board[card1 - 1].getValue() > 10) {
							System.out.println("Sheesh you're stupid");
							System.exit(0);
						}
						Board[cardTemp - 1] = null;
						System.out.println("Board:");
						System.out.println("_____________________________");
						for(int d = 0; d < 52; d++) {
							if(Board[d] != null) {
								if(Triplebuild[d] != null) {
									if(Held[d] != null) {
										System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d] + "; held with card: " + Held[d]);
										}else {
											System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d]);
											}
									} else {
										if(Doublebuild[d] != null) {
											if(Held[d] != null) {
												System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d] + "; held with card: " + Held[d]);
												}else {
													System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d]);
												}
										}else {
											if(Build[d] != null) {
												if(Held[d] != null) {
													System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d] + "; held with card: " + Held[d]);
													}else {
														System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d]);
													}
											}else {
												System.out.println((d+1) + ") " + Board[d]);
											}
										
									}
										
							}}
						}
						System.out.println("_____________________________");
					}
						System.out.println("Would you like to 1) Pick; 2) Diss; 3) Build; 4) Hold; 5); Build from board 6) Pick multiple from board");
						Play = scan.nextInt();
				}
				if(Play == 1) {
					System.out.println("Which card would you like to pick from your hand?");
					handchoice = scan.nextInt();
					System.out.println("Which card would you like to pick from the board?");
					choice = scan.nextInt();
					if(Hand1[handchoice - 1].getValue() == Board[choice - 1].getValue()) {
						Pocket1[Pocket1index] = Hand1[handchoice - 1];
						Pocket1index++;
						Pocket1[Pocket1index] = Board[choice - 1];
						Pocket1index++;
						Board[choice - 1] = null;
						Hand1[handchoice - 1] = null;
						if(Held[choice - 1] != null) {
							Pocket1[Pocket1index] = Held[choice - 1];
							Pocket1index++;
							Held[choice - 1] = null;
						}
						if (Build[choice - 1] != null) {
							Pocket1[Pocket1index] = Build[choice - 1];
							Pocket1index++;
							Build[choice - 1] = null;
							if (Doublebuild[choice - 1] != null) {
								Pocket1[Pocket1index] = Doublebuild[choice - 1];
								Pocket1index++;
								Doublebuild[choice - 1] = null;
								if (Triplebuild[choice - 1] != null) {
									Pocket1[Pocket1index] = Triplebuild[choice - 1];
									Pocket1index++;
									Triplebuild[choice - 1] = null;
							}
						}
					}
					}
					else {
						System.out.println("I'm not an idiot but now you have to restart the game");
						System.exit(0);
						}
				}
				else if(Play == 2) {
					System.out.println("Which card would you like to diss from your hand?");
					handchoice = scan.nextInt();
					for (int l = 51; l >= 0; l--) {
						if (Board[l] == null) {
							tempindex = l;
						}
					}
					Board [tempindex] = Hand1[handchoice - 1];
					Hand1[handchoice - 1] = null;
				}
				else if(Play == 3){
					System.out.println("Which card would you like to build from your hand?");
					handchoice = scan.nextInt();
					System.out.println("Which card would you like to build on top of from the board?");
					choice = scan.nextInt();
					if(Board[choice - 1].getHoldValue() > 0) {
						System.out.println("This card is held, pay attention and restart the game");
						System.exit(0);
					}
					if (Build[choice - 1] != null && Doublebuild[choice - 1] != null) {
						Triplebuild[choice - 1] = Doublebuild[choice - 1];
						Doublebuild[choice - 1] = Build[choice - 1];
					}else {
						if(Build[choice - 1] != null) {
							Doublebuild[choice - 1] = Build[choice - 1];
						}
					}
					Board[choice - 1].Build(Hand1[handchoice - 1].getValue());
					System.out.println(Board[choice - 1]);
					Build[choice - 1] = Hand1[handchoice - 1];
					Hand1[handchoice - 1] = null;	
				}
				else if(Play == 4){
					System.out.println("Which card would you like to hold from your hand?");
					handchoice = scan.nextInt();
					System.out.println("Which card would you like to hold from the board?");
					choice = scan.nextInt();
					if(Board[choice - 1].getValue() == Hand1[handchoice - 1].getValue());{
						Board[choice - 1].Held();
						Held[choice - 1] = Hand1[handchoice - 1];
						Hand1[handchoice - 1] = null;
					}
				}else if (Play == 6) {
					System.out.println("Which card would you like to pick from your hand?");
					handchoice = scan.nextInt();
					Pocket1[Pocket1index] = Hand1[handchoice - 1];
					Pocket1index++;
					System.out.println("How many cards would you like to pick? (only count the rows, no cards under the base card)");
					amount = scan.nextInt();
					int cardTemp;
					for (int p = 0; p < amount; p++) {
						System.out.println("Pick card #" + (p + 1));
						cardTemp = scan.nextInt();
						if(Board[cardTemp - 1].getValue() == Hand1[handchoice - 1].getValue()) {
							Pocket1[Pocket1index] = Board[cardTemp - 1];
							Pocket1index++;
							Board[cardTemp - 1] = null;
							if (Build[cardTemp - 1] != null) {
								Pocket1[Pocket1index] = Build[cardTemp - 1];
								Pocket1index++;
								Build[cardTemp - 1] = null;
								if (Doublebuild[cardTemp - 1] != null) {
									Pocket1[Pocket1index] = Doublebuild[cardTemp - 1];
									Pocket1index++;
									Doublebuild[cardTemp - 1] = null;
									if (Triplebuild[cardTemp - 1] != null) {
										Pocket1[Pocket1index] = Triplebuild[cardTemp - 1];
										Pocket1index++;
										Triplebuild[cardTemp - 1] = null;
									}
								}
							}
						}
					}
					Hand1[handchoice - 1] = null;
				}
				else {
					System.out.println("I'm not an idiot but now you have to restart the game");
					System.exit(0);
				}
				System.out.println("Board:");
				System.out.println("_____________________________");
				for(int d = 0; d < 52; d++) {
					if(Board[d] != null) {
						if(Triplebuild[d] != null) {
							if(Held[d] != null) {
								System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d] + "; held with card: " + Held[d]);
								}else {
									System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d]);
									}
							} else {
								if(Doublebuild[d] != null) {
									if(Held[d] != null) {
										System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d] + "; held with card: " + Held[d]);
										}else {
											System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d]);
										}
								}else {
									if(Build[d] != null) {
										if(Held[d] != null) {
											System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d] + "; held with card: " + Held[d]);
											}else {
												System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d]);
											}
									}else {
										System.out.println((d+1) + ") " + Board[d]);
									}
								
							}
								
					}}
				}
				System.out.println("_____________________________");
				System.out.println("Player 2 Hand: ");
				System.out.println("-----------------------------");
				for (int hand = 0; hand < 4; hand++) {
					if(Hand2[hand] == null) {
						System.out.println((hand+1) + ") Used");
					}else {
						System.out.println((hand+1) + ") " + Hand2[hand]);
					}
				}
				System.out.println("-----------------------------");
				System.out.println("Player 2, " + "Turn " + (i+1) + ": ");
				System.out.println("Would you like to 1) Pick; 2) Diss; 3) Build; 4) Hold; 5); Build from board 6) Pick multiple from board");
				Play = scan.nextInt();
				while (Play == 5) {
					System.out.println("How many cards would you like to build?");
					amount = scan.nextInt();
					System.out.println("Pick card #1");
					int card1 = scan.nextInt();
					int cardTemp;
					for (int p = 0; p < amount - 1; p++) {
						System.out.println("Pick card #" + (p + 2));
						cardTemp = scan.nextInt();
					
						if(Build[card1 - 1] == null) {
							Board[card1 - 1].Build(Board[cardTemp - 1].getValue());
							Build[card1 - 1] = Board[cardTemp - 1];
						}else {
							if(Doublebuild[card1 - 1] == null) {
							Board[card1 - 1].Build(Board[cardTemp - 1].getValue());
							Doublebuild[card1 - 1] = Board[cardTemp - 1];
							Build[cardTemp - 1] = null;
							}else {
								Triplebuild[card1 - 1] = Board[cardTemp - 1];
							}
							
						}
						if(Board[card1 - 1].getValue() > 10) {
							System.out.println("Sheesh you're stupid");
							System.exit(0);
						}
						Board[cardTemp - 1] = null;
						System.out.println("Board:");
						System.out.println("_____________________________");
						for(int d = 0; d < 52; d++) {
							if(Board[d] != null) {
								if(Triplebuild[d] != null) {
									if(Held[d] != null) {
										System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d] + "; held with card: " + Held[d]);
										}else {
											System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d]);
											}
									} else {
										if(Doublebuild[d] != null) {
											if(Held[d] != null) {
												System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d] + "; held with card: " + Held[d]);
												}else {
													System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d]);
												}
										}else {
											if(Build[d] != null) {
												if(Held[d] != null) {
													System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d] + "; held with card: " + Held[d]);
													}else {
														System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d]);
													}
											}else {
												System.out.println((d+1) + ") " + Board[d]);
											}
										
									}
										
							}}
						}
						System.out.println("_____________________________");
					}
					System.out.println("Would you like to 1) Pick; 2) Diss; 3) Build; 4) Hold; 5); Build from board 6) Pick multiple from board");
					Play = scan.nextInt();
				}
				if(Play == 1) {
					System.out.println("Which card would you like to pick from your hand?");
					handchoice = scan.nextInt();
					System.out.println("Which card would you like to pick from the board?");
					choice = scan.nextInt();
					if(Hand2[handchoice - 1].getValue() == Board[choice - 1].getValue()) {
						Pocket2[Pocket2index] = Hand2[handchoice - 1];
						Pocket2index++;
						Pocket2[Pocket2index] = Board[choice - 1];
						Pocket2index++;
						Board[choice - 1] = null;
						Hand2[handchoice - 1] = null;
						if(Held[choice - 1] != null) {
							Pocket2[Pocket2index] = Held[choice - 1];
							Pocket2index++;
							Held[choice - 1] = null;
						}
						if (Build[choice - 1] != null) {
							Pocket2[Pocket2index] = Build[choice - 1];
							Pocket2index++;
							Build[choice - 1] = null;
							if (Doublebuild[choice - 1] != null) {
								Pocket2[Pocket2index] = Doublebuild[choice - 1];
								Pocket2index++;
								Doublebuild[choice - 1] = null;
								if (Triplebuild[choice - 1] != null) {
									Pocket2[Pocket2index] = Triplebuild[choice - 1];
								Pocket2index++;
									Triplebuild[choice - 1] = null;
							}}
					}
					}
					else {
						System.out.println("I'm not an idiot but now you have to restart the game");
						System.exit(0);
					}
				}
				else if(Play == 2) {
					System.out.println("Which card would you like to diss from your hand?");
					handchoice = scan.nextInt();
					for (int l = 51; l >= 0; l--) {
						if (Board[l] == null) {
							tempindex = l;
						}
					}
					Board [tempindex] = Hand2[handchoice - 1];
					Hand2[handchoice - 1] = null;
				}
				else if(Play == 3){
					System.out.println("Which card would you like to build from your hand?");
					handchoice = scan.nextInt();
					System.out.println("Which card would you like to build on top of from the board?");
					choice = scan.nextInt();
					if(Board[choice - 1].getHoldValue() > 0) {
						System.out.println("This card is held, pay attention and restart the game");
						System.exit(0);
					}
					if (Build[choice - 1] != null) {
						Doublebuild[choice - 1] = Build[choice - 1];
					}
					Board[choice - 1].Build(Hand2[handchoice - 1].getValue());
					Build[choice - 1] = Hand2[handchoice - 1];
					Hand2[handchoice - 1] = null;	
				}
				else if(Play == 4){
					System.out.println("Which card would you like to hold from your hand?");
					handchoice = scan.nextInt();
					System.out.println("Which card would you like to hold from the board?");
					choice = scan.nextInt();
					if(Board[choice - 1].getValue() == Hand2[handchoice - 1].getValue());{
						Board[choice - 1].Held();
						Held[choice - 1] = Hand2[handchoice - 1];
						Hand2[handchoice - 1] = null;
					}
				}
				else if (Play == 6) {
					System.out.println("Which card would you like to pick from your hand?");
					handchoice = scan.nextInt();
					Pocket2[Pocket2index] = Hand2[handchoice - 1];
					Pocket2index++;
					System.out.println("How many cards would you like to pick?");
					amount = scan.nextInt();
					int cardTemp;
					for (int p = 0; p < amount; p++) {
						System.out.println("Pick card #" + (p + 1));
						cardTemp = scan.nextInt();
						if(Board[cardTemp - 1].getValue() == Hand2[handchoice - 1].getValue()) {
							Pocket2[Pocket2index] = Board[cardTemp - 1];
							Pocket2index++;
							Board[cardTemp - 1] = null;
							if (Build[cardTemp - 1] != null) {
								Pocket2[Pocket2index] = Build[cardTemp - 1];
								Pocket2index++;
								Build[cardTemp - 1] = null;
								if (Doublebuild[cardTemp - 1] != null) {
									Pocket2[Pocket2index] = Doublebuild[cardTemp - 1];
									Pocket2index++;
									Doublebuild[cardTemp - 1] = null;
									if (Triplebuild[cardTemp - 1] != null) {
										Pocket2[Pocket2index] = Triplebuild[cardTemp - 1];
										Pocket2index++;
										Triplebuild[cardTemp - 1] = null;
									}
								}
							}
						}
					}
					Hand2[handchoice - 1] = null;
				}
				else {
					System.out.println("I'm not an idiot but now you have to restart the game");
					System.exit(0);
				}
				System.out.println("Board:");
				System.out.println("_____________________________");
				for(int d = 0; d < 52; d++) {
					if(Board[d] != null) {
						if(Triplebuild[d] != null) {
							if(Held[d] != null) {
								System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d] + "; held with card: " + Held[d]);
								}else {
									System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d]);
									}
							} else {
								if(Doublebuild[d] != null) {
									if(Held[d] != null) {
										System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d] + "; held with card: " + Held[d]);
										}else {
											System.out.println((d+1) + ") " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d]);
										}
								}else {
									if(Build[d] != null) {
										if(Held[d] != null) {
											System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d] + "; held with card: " + Held[d]);
											}else {
												System.out.println((d+1) + ") " + Board[d] + "; Built with card: " + Build[d]);
											}
									}else {
										System.out.println((d+1) + ") " + Board[d]);
									}
								
							}
								
					}}
				}
				System.out.println("_____________________________");
			}
		}
		for(int i = 0; i < Pocket1index; i++) {
			if (Pocket1[i].getPoints() == 1) {
				P1score++;
				if(Pocket1[i].getPoints() == 2) {
					P1spades++;
				}
			}else if (Pocket1[i].getPoints() == 2) {
				P1spades++;
			}else if (Pocket1[i].getPoints() == 3) {
				P1score += 2;
			}
		}
		for(int i = 0; i < Pocket2index; i++) {
			if (Pocket2[i].getPoints() == 1) {
				P2score++;
				if(Pocket2[i].getPoints() == 2) {
					P2spades++;
				}
			}else if (Pocket2[i].getPoints() == 2) {
				P1spades++;
			}else if (Pocket2[i].getPoints() == 3) {
				P2score += 2;
			}
		}
		if (Pocket1index > Pocket2index) {
			P1score += 3;
		}else if (Pocket1index < Pocket2index) {
			P2score += 3;
			
			if (P1spades > P2spades) {
				P1score += 1;
			}else {
				P2score += 1;
			}
		}
		if (P1score > P2score) {
			System.out.println("Player 1 is the winner!");
		}else if (P2score > P1score) {
			System.out.println("Player 2 is the winner!");
		}else {
			System.out.println("It's a tie!");
		}
		System.out.println("Here are the final scores: Player 1 with " + P1score + " points and Player 2 with " + P2score + " points!");
	}
	}
