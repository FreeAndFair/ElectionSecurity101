voting_system_choice(voting_systems) ::= <<
<div align="center">
<table border=0>
<th><td colspan="2">Choose a Voting System</td></th>
$voting_systems:{ system |
  <tr><td><a href="/$system.schema$">$system.name$</a></td><td>$system.usage_regions$</td></tr> 
 }$
 </table>
</div>
>>