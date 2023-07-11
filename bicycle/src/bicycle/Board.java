System.out.println("Board:");
						System.out.println("_____________________________");
						for(int d = 0; d < 52; d++) {
							if(Board[d] != null) {
								if(Triplebuild[d] != null) {
									if(Held[d] != null) {
										System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d] + "; held with card: " + Held[d]);
										}else {
											System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + Board[d] + "; Built with cards: " + Build[d] + ", " + Doublebuild[d] + ", and " + Triplebuild[d]);
											}
									} else {
										if(Doublebuild[d] != null) {
											if(Held[d] != null) {
												System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d] + "; held with card: " + Held[d]);
												}else {
													System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + Board[d] + "; Built with cards: " + Build[d] + ", and " + Doublebuild[d]);
												}
										}else {
											if(Build[d] != null) {
												if(Held[d] != null) {
													System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + Board[d] + "; Built with card: " + Build[d] + "; held with card: " + Held[d]);
													}else {
														System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + Board[d] + "; Built with card: " + Build[d]);
													}
											}else {
												System.out.println((d+1) + ") Value: " + Board[d].getValue() + " " + + Board[d]);
											}
										
									}
										
							}}
						}