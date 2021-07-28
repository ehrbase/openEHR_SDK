
grammar Flat;



path: pathEndSegment | (pathSegment SLASH)+ pathEndSegment;


pathSegment: TEXT (COLON DIGITS)?;
pathEndSegment: pathSegment (VERTICAL_BAR attribute)?;

attribute:TEXT (TEXT | SLASH | VERTICAL_BAR| DIGITS | COLON )*;


//
// LEXER PATTERNS
//

SLASH  : '/';
VERTICAL_BAR: '|';
COLON : ':';
TEXT  : ('a'..'z' | 'A'..'Z' | '0'..'9' | '_' | '.' | '-')+;
DIGITS : '1'..'9' '0'..'9'*;


