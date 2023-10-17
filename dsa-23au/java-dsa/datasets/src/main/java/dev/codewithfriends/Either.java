package dev.codewithfriends;

import java.util.function.Function;

public class Either<L,R> {

    private L left;
    private R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Either<L,R> tryLeftExceptRight(Function<L,L> leftFunc, Function<Exception,R> rightFunc) {
        L newLeft = this.left;
        R newRight = this.right;
        try {
            newLeft = leftFunc.apply(this.left);
        } catch (Exception e) {
            newRight = rightFunc.apply(e);
        }
        return new Either<L,R>(newLeft, newRight);
    }
    public Either<L,R> map(Function<L,L> leftFunc, Function<R,R> rightFunc ) {
        return new Either<L,R>(leftFunc.apply(this.left), rightFunc.apply(this.right));
    }
}
