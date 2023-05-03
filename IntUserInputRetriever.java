// Create IntUserInputRetriever interface here
public interface IntUserInputRetriever<T>{
  abstract T produceOutputOnIntUserInput(int selection) throws IllegalArgumentException;
}
