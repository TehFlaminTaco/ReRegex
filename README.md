# ReRegex
An Esoteric Programming Language which is just Java's regex repeating again and again...

Code is split by `/` and pairs are saved. The remaning code is what is executed.

Pairs of code such as `a/b` are saved as Regular Expressions, being `match` and `replace`, following Java's regular expression rules, for the most part.

Newlines, `(?#Comments)` and `#Comments\n` are all removed before execution, the `\n` literal still works, this is to make code actually readable.

`a/b/some probably really long string` is a very basic program which replaces all the `a`s with `b`s in `some probably really long string`.

Because ReRegex repeats doing the regexes until execution halts, it's rather simple to make a program loop.

`(1*)/$11/either 0 or 1` is your truth machine, although it will never output, if you supply it a `1`, it will replace it with infinite `1`s, increasing 1 at a time. If you supply it a `0`, it just outputs the `0`, then and there.

Math is best done in Unary, where you only need to think about one digit.

```
#Convert to Base1
u\{0(\d*)(_*)\}/u{$1mult10{$2}}/
u\{1(\d*)(_*)\}/u{$1mult10{$2}_}/
u\{2(\d*)(_*)\}/u{$1mult10{$2}__}/
u\{3(\d*)(_*)\}/u{$1mult10{$2}___}/
u\{4(\d*)(_*)\}/u{$1mult10{$2}____}/
u\{5(\d*)(_*)\}/u{$1mult10{$2}_____}/
u\{6(\d*)(_*)\}/u{$1mult10{$2}______}/
u\{7(\d*)(_*)\}/u{$1mult10{$2}_______}/
u\{8(\d*)(_*)\}/u{$1mult10{$2}________}/
u\{9(\d*)(_*)\}/u{$1mult10{$2}_________}/
u\{(_*)\}/$1/

#Convert to base 10
d\{((_{10})*)(\d*)\}/d{over10{$1}0$3}/
d\{((_{10})*)_(\d*)\}/d{over10{$1}1$3}/
d\{((_{10})*)__(\d*)\}/d{over10{$1}2$3}/
d\{((_{10})*)___(\d*)\}/d{over10{$1}3$3}/
d\{((_{10})*)____(\d*)\}/d{over10{$1}4$3}/
d\{((_{10})*)_____(\d*)\}/d{over10{$1}5$3}/
d\{((_{10})*)______(\d*)\}/d{over10{$1}6$3}/
d\{((_{10})*)_______(\d*)\}/d{over10{$1}7$3}/
d\{((_{10})*)________(\d*)\}/d{over10{$1}8$3}/
d\{((_{10})*)_________(\d*)\}/d{over10{$1}9$3}/
d\{over10\{\}(\d*)\}/$1/

#Multiply by 10.
mult10\{(_*)\}/$1$1$1$1$1$1$1$1$1$1/

#Divide by 10. Intentionally leaves a over10{} left over
over10\{(_*)(_{10})(:(_*))?\}/over10{$1:_$4}/
over10\{_{0,9}(:?(_+))}/$2/
```

The above snippit is useful, as it implements `u{decimal_number}` and `d{unary_number}` to convert between the two, unary in this case is represented with `_`s.

Nothing is provided to you other then regex, good luck.
