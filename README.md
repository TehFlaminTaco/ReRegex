# ReRegex
An Esoteric Programming Language which is just Java's regex repeating again and again...

Code is split by `/` and pairs are saved. The remaning code is what is executed.

Pairs of code such as `a/b` are saved as Regular Expressions, being `match` and `replace`, following Java's regular expression rules, for the most part.

Newlines, `(?#Comments)` and `#Comments\n` are all removed before execution, the `\n` literal still works, this is to make code actually readable.

`a/b/some probably really long string` is a very basic program which replaces all the `a`s with `b`s in `some probably really long string`.

Because ReRegex repeats doing the regexes until execution halts, it's rather simple to make a program loop.

`(1*)/$11/either 0 or 1` is your truth machine, although it will never output, if you supply it a `1`, it will replace it with infinite `1`s, increasing 1 at a time. If you supply it a `0`, it just outputs the `0`, then and there.

You can import various librarias via either `#import lib` or `(?#import lib)`, These just contain preset match/replace pairs. The most commonly used one should be `math`, which contains operators for both unary and decimal, as well as methods to convert between the two. (All written in ReRegex, of course).

And lastly, you can take input via either `(?#input)` or `#input`, which can be used anywhere if you really feel like it, but will probably be used on the code line.