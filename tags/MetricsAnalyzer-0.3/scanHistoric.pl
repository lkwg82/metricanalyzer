#!perl
$|=1;
use strict;
use warnings 'all';

my $dir = shift;
my $minRevision=0;
my $sonarVersion="2.8";
my %revision = ();

chdir($dir) || die "could not change to $dir";

print `svn up `;
open(CMD,"svn log |") || print $^E;
my $lastDate=0;
while(<CMD>){
	#print $_;
	
	if ( /^r(\d+)\ \|.+?\|\ (\d+-\d+-\d+).+/ ){
		#print "$1 $2\n";
		
		if ( $1 >= $minRevision ){
			if ( $2 ne $lastDate){
				$revision{$1} = $2;
				$lastDate = $2;
			}
		}
	}
}
close(CMD);

grep{
	my $rev = $_;
	my $date = $revision{$rev};
	#print "----- \n";
	#print "$date \n";
	my $svnCmd = "svn up -r $rev";
	my $sonarCmd ="mvn clean compile org.codehaus.sonar:sonar-maven-plugin:$sonarVersion:sonar -Dsonar.projectDate=$date -Dsonar.projectVersion=r$rev";
	
	#print $svnCmd,"\n";
	print `$svnCmd`;
	#print $sonarCmd,"\n";
	print `$sonarCmd`;
	
}reverse sort keys %revision;