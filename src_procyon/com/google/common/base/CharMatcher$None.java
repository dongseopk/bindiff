package com.google.common.base;

final class CharMatcher$None extends CharMatcher$NamedFastMatcher
{
    static final CharMatcher$None INSTANCE;
    
    private CharMatcher$None() {
        super("CharMatcher.none()");
    }
    
    @Override
    public boolean matches(final char c) {
        return false;
    }
    
    @Override
    public int indexIn(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return -1;
    }
    
    @Override
    public int indexIn(final CharSequence charSequence, final int n) {
        Preconditions.checkPositionIndex(n, charSequence.length());
        return -1;
    }
    
    @Override
    public int lastIndexIn(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return -1;
    }
    
    @Override
    public boolean matchesAllOf(final CharSequence charSequence) {
        return charSequence.length() == 0;
    }
    
    @Override
    public boolean matchesNoneOf(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return true;
    }
    
    @Override
    public String removeFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final char c) {
        return charSequence.toString();
    }
    
    @Override
    public String replaceFrom(final CharSequence charSequence, final CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence2);
        return charSequence.toString();
    }
    
    @Override
    public String collapseFrom(final CharSequence charSequence, final char c) {
        return charSequence.toString();
    }
    
    @Override
    public String trimFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public String trimLeadingFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public String trimTrailingFrom(final CharSequence charSequence) {
        return charSequence.toString();
    }
    
    @Override
    public int countIn(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return 0;
    }
    
    @Override
    public CharMatcher and(final CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return this;
    }
    
    @Override
    public CharMatcher or(final CharMatcher charMatcher) {
        return (CharMatcher)Preconditions.checkNotNull(charMatcher);
    }
    
    @Override
    public CharMatcher negate() {
        return CharMatcher.any();
    }
    
    static {
        INSTANCE = new CharMatcher$None();
    }
}
