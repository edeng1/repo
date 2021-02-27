/**
 *Converts infix expressions to postfix expressions, postfix expressions to infix expressions,
 * and evaluates a postfix expression as a double
 * @author Eric Deng
 *
 */
public class Notation {


    /**
     *
     * @param infix the infix expression in string format
     * @return the postfix expression in string format
     * @throws InvalidNotationFormatException if the infix expression format is invalid
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

        NotationQueue<Character> postfixQueue= new NotationQueue();
        NotationStack<Character> infixStack=new NotationStack();
        //Remove spaces
        infix.replaceAll(" ", "");
        try {


            for (int i = 0; i < infix.length(); i++) {

                char ch = infix.charAt(i);

                //Digits
                if (Character.isDigit(ch)) {
                    postfixQueue.enqueue(ch);
                }
                //Left parenthesis
                else if (ch == '(') {
                    infixStack.push(ch);
                }
                //Operators
                else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    if(!infixStack.isEmpty())
                    {

                        if (infixStack.top() == '+' || infixStack.top() == '-' || infixStack.top() == '*' || infixStack.top() == '/') {
                            infixStack.pop();
                        }
                    }

                    infixStack.push(ch);
                }
                //Right parenthesis
                else if (ch == ')') {
                    try {

                        while (infixStack.top() == '+' || infixStack.top() == '-' || infixStack.top() == '*' || infixStack.top() == '/') {
                            postfixQueue.enqueue(infixStack.pop());


                        }

                        if (infixStack.top() == '(') {
                            infixStack.pop();


                        }
                    }
                    catch(StackUnderflowException e) //if top of stack is empty
                    {
                        throw new InvalidNotationFormatException();
                    }






                    }


            }
        }
        catch (StackUnderflowException e) {
//            throw new InvalidNotationFormatException();
        }
        catch (StackOverflowException e) {

        }
        catch (QueueOverflowException e) {

        }
        return postfixQueue.toString();
    }

    /**
     *
     * @param postfix the postfix expression in string format
     * @return the infix expression in string format
     * @throws InvalidNotationFormatException if the postfix expression format is invalid
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException{
        NotationStack<String> stack=new NotationStack();
        String expr="";
        String val1="";
        String val2="";

        postfix.replaceAll(" ","");
        try {
            for (int i = 0; i < postfix.length(); i++) {
                char ch = postfix.charAt(i);

                if (Character.isDigit(ch)) {

                    stack.push(String.valueOf(ch));

                }

                if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    if (stack.size() < 2) {
                        throw new InvalidNotationFormatException();
                    } else {


                        val2 = stack.pop();



                        val1 = stack.pop();

                        expr = "(" + val1 + ch + val2 + ")";

                        stack.push(expr);

                    }

                }


            }
        }
        catch(StackOverflowException e) {

        }
        catch (StackUnderflowException e) {

        }

        if(stack.size()>1)
        {
            throw new InvalidNotationFormatException();
        }
        else
            return stack.toString();

    }

    /**
     *
     * @param postfix the postfix expression in String format
     * @return the evaluation of the postfix expression as a double
     * @throws InvalidNotationFormatException if the postfix expression format is invalid
     */
    public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException{
        NotationStack<String> stack=new NotationStack();
        postfix.replaceAll(" ","");
        double top=0;
        double result=0;

        try {
            for (int i = 0; i < postfix.length(); i++) {
                char ch = postfix.charAt(i);
                if (Character.isDigit(ch) || ch == '(') {
                    stack.push(String.valueOf(ch));

                }


                else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                    if (stack.size() < 2) {
                        throw new InvalidNotationFormatException();
                    }
                    else {

                        double rightOperand= Double.valueOf(stack.pop());
                        double leftOperand= Double.valueOf(stack.pop());

                        if(ch == '+')
                        {
                            result=leftOperand+rightOperand;
                        }
                        else if(ch == '-')
                        {
                            result=leftOperand-rightOperand;
                        }
                        else if(ch == '*')
                        {
                            result=leftOperand*rightOperand;
                        }
                        else if(ch == '/')
                        {
                            result=leftOperand/rightOperand;
                        }
                        stack.push(String.valueOf(result));

                    }


                }

            }//end loop
            if(stack.size()>1)
            {
                throw new InvalidNotationFormatException();
            }
            else
                top=Double.valueOf(stack.top());
        }
        catch(StackOverflowException e){

        }
        catch(StackUnderflowException e){

        }
        return top;
    }
}
