i_voted(election, explanation, voter_action, receipt) ::= <<
<p align="center"><em>(scroll down for the current election results)</em></p>
<h3>You voted with: $voter_action.votingSystem.name$</h3>

$explanation$

<!--
$if(voter_action.votingSystem.receiptGenerated)$
  <h4>Your $voter_action.votingSystem.receiptName$:</h4>
  <h5>$receipt$</h5>
$else$
  <h4>With $voter_action.votingSystem.name$, you do not receive any
      confirmation of your vote.</h4>
$endif$
-->

$tally(election.tally)$

<div align="center">

<h4>$election.voteManipulationCount$ of $election.voteCount$ votes cast in this election have been hacked.</h4>

<p>Note that it takes some time for votes to be tallied, and your vote
may not yet appear in these totals.</p>

<button type="button" class="medium_button"
 onclick="window.location.href='/'">Thank You for Voting! Make Sure You Get An "I Voted" Sticker!</button>
</div>
>>