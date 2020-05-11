package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {
	public static MultiSet<String> parse(String filename) throws InvalidMultiSetFormat {
		MultiSet<String> base = new HashMultiSet<>();
		MultiSet<String> ms = new MultiSetDecorator<>(base);
		BufferedReader reader = null;
		int lineNb = 0;
		try {
			String line;
			Integer no = 0;
			reader = new BufferedReader(new FileReader(filename));
			while((line = reader.readLine()) !=null) {
				lineNb++;
				String [] str = line.split(":", 2);
				no = Integer.decode(str[1]);
				ms.add(str[0], no);
			}
			reader.close();
		}catch (FileNotFoundException fnfEx) {
			throw new InvalidMultiSetFormat("Error : File "+filename+" could not be found,"
					+ " please check the provided filename is correct", fnfEx.getCause());
		} catch (IOException ioex) {
			throw new InvalidMultiSetFormat("Error : invalid MultiSet format, see line "+lineNb, ioex.getCause());
		} catch (NumberFormatException numFormatEx) {
			throw new InvalidMultiSetFormat("Error : has to be an Integer, after ':' at line "+lineNb);
		}
		catch(ArrayIndexOutOfBoundsException boundsEx) {
			throw new InvalidMultiSetFormat("Error : Invalid MultiSet format, failed at line "+lineNb+
					" check that there is something after ':'", boundsEx.getCause());
		}catch(Exception e) {
			throw new InvalidMultiSetFormat("Error : Invalid MultiSet format, failed at line "+lineNb, e.getCause());
		}
		return ms;
	}
}
