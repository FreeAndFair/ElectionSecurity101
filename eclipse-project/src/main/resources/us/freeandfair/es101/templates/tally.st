tally(candidate_0_winning, candidate_0, votes_0, candidate_1_winning, candidate_1, votes_1) ::= <<
<div align="center">
<b>Current Results</b>

<p>
$if(candidate_0_winning)$
<b>$candidate_0$</b>
$else$
$candidate_0$
$endif$
$votes_0$ &ndash; $votes_1$
$if(candidate_1_winning)$
<b>
$candidate_1$
</b>
$else$
$candidate_1$
$endif$
</p>
</div>
>>